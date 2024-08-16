/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.dao;

import com.webtools.ayasha.WebProject.model.Contributor;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayashakhan
 */

@Repository
@Qualifier("contributorDAO")
public class ContributorDAO extends BaseDAO{
    
    public List<Contributor> getAllContributor() {
//        Session session = getSession();
//        return session.createQuery("FROM Contributor", Contributor.class).list();
//        
        
//        beginTransaction();
        Query query = getSession().createQuery("FROM Contributor", Contributor.class);
        List<Contributor> contributorList = query.getResultList();
        closeSession();
        return contributorList;
    }
    
    public Contributor findByEmailId(String emailId) {
            return getSession()
                    .createNamedQuery("selectByContributorEmailId", Contributor.class)
                    .setParameter("emailId", emailId)
                    .uniqueResult();
        }

    public Optional<Contributor> findById(long contributorId) {
        Contributor contributor = getSession().get(Contributor.class, contributorId);
        return Optional.ofNullable(contributor);
    }

    public void saveContributor(Contributor contributor) {
        try {
            beginTransaction();
            getSession().save(contributor);
            commitTransaction();
            System.out.println("Contributor saved successfully");
        } catch (Exception e) {
            System.err.println("Error saving contributor: " + e.getMessage());
            e.printStackTrace();
            if (getSession().getTransaction() != null) {
                getSession().getTransaction().rollback();
            }
            throw e;
        } finally {
            closeSession();
        }
    }


    public void updateContributor(Contributor contributor) {
        beginTransaction();
        getSession().update(contributor);
        commitTransaction();
        closeSession();
    }

    public void deleteContributor(Contributor contributor) {
        beginTransaction();
        getSession().delete(contributor);
        commitTransaction();
        closeSession();
    }

    
}
