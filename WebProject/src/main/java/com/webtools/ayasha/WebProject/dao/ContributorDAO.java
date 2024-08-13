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
    
    public Optional<Contributor> findByEmailId(String emailId) {
            return getSession()
                    .createNamedQuery("selectByContributorEmailId", Contributor.class)
                    .setParameter("emailId", emailId)
                    .uniqueResultOptional();
        }

    public Optional<Contributor> findById(long contributorId) {
        Contributor contributor = getSession().get(Contributor.class, contributorId);
        return Optional.ofNullable(contributor);
    }

    public void save(Contributor contributor) {
        beginTransaction();
        getSession().save(contributor);
        commitTransaction();
        closeSession();
    }

    public void update(Contributor contributor) {
        beginTransaction();
        getSession().update(contributor);
        commitTransaction();
        closeSession();
    }

    public void delete(Contributor contributor) {
        beginTransaction();
        getSession().delete(contributor);
        commitTransaction();
        closeSession();
    }
    
}