/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.service;

import com.webtools.ayasha.WebProject.dao.ContributorDAO;
import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.model.Student;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayashakhan
 */
@Component
public class ContributorService {
    
    private final ContributorDAO contributorDAO;

    @Autowired
    public ContributorService(ContributorDAO contributorDAO) {
        this.contributorDAO = contributorDAO;
    }

    @Transactional
    public List<Contributor> getAllContributor() {
        return contributorDAO.getAllContributor();
    }
    
    public Optional<Contributor> findById(long contributorId) {
        return contributorDAO.findById(contributorId);
    }

    public Contributor findByEmailId(String emailId) {
        return contributorDAO.findByEmailId(emailId);
    }

    public void saveContributor(Contributor contributor) {
        contributorDAO.save(contributor);
    }

    public void updateContributor(Contributor contributor) {
        contributorDAO.update(contributor);
    }

    public void deleteContributor(Contributor contributor) {
        contributorDAO.delete(contributor);
    }
    
}
