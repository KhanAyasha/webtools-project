/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.service.ContributorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ayashakhan
 */

@Controller
@RequestMapping("/contributor")
public class ContributorController {
    
    private final ContributorService contributorService;

    @Autowired
    public ContributorController(ContributorService contributorService) {
        this.contributorService = contributorService;
    }
    
    @GetMapping("/contributors")
    @ResponseBody
    public List<Contributor> getAllContributor() {
        
        return contributorService.getAllContributor();
    }
    
    
    @GetMapping("/home.htm")
    public String homePage(HttpSession session, Model model) {
        System.out.println("hit home contributor");
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        return "home";
    }
    
    @PutMapping("/update/{contributorId}")
    public ResponseEntity<String> updateContributor(@PathVariable long contributorId, @RequestBody Contributor updatedContributor) {
        // Find the contributor by ID
        Optional<Contributor> contributorOptional = contributorService.findById(contributorId);
        if (contributorOptional.isPresent()) {
            Contributor existingContributor = contributorOptional.get();
            
            // Update the fields
            existingContributor.setFirstName(updatedContributor.getFirstName());
            existingContributor.setLastName(updatedContributor.getLastName());
            

            // Update the contributor in the database
            contributorService.updateContributor(existingContributor);
            return ResponseEntity.ok("Contributor updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Contributor not found.");
        }
    }
    
    
    @GetMapping("/logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/login.htm?logout=true";
    }
}
