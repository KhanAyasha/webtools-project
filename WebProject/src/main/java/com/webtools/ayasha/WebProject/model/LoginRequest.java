/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

/**
 *
 * @author ayashakhan
 */
public class LoginRequest {
    private String emailId;
    private String password;
    private String role;

    public LoginRequest() {
    }

    public LoginRequest(String emailId, String password, String role) {
        this.emailId = emailId;
        this.password = password;
        this.role = role;
    }
    
    

    // Getters and setters
    
    

    public String getEmailId() {
        return emailId;
    }

    public void setmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}

    

