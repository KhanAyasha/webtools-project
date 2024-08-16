/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.Check;

/**
 *
 * @author ayashakhan
 */
@Entity
@Table(name = "Contributor", uniqueConstraints = @UniqueConstraint(columnNames = { "emailId"}))
@NamedQueries({
        @NamedQuery(name = "selectByContributorEmailId", query = "FROM Contributor WHERE emailId = :emailId")
})
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long contributorId;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @Basic(optional = false)
    @Column
    private String password;

    @Basic(optional = false)
    @Column
    private String emailId;

    @Column
    @Check(constraints = "role = 'contributor'")
    private String role = "contributor";

    @Column
    private String expertise;

    @Column
    private int experienceYears;
    
    @Column
    private String major;
    
//    // One-to-Many relationship with CourseContributor
//    @OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
//    private List<Courses> courses;

    public Contributor() {}

    public Contributor(String firstName, String lastName, String password, String emailId, String expertise, int experienceYears, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.expertise = expertise;
        this.experienceYears = experienceYears;
        this.major = major;
    }

    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
    
//    public List<Courses> getCourseContributors() {
//        return courses;
//    }
//
//    public void setCourseContributors(List<Courses> courses) {
//        this.courses = courses;
//    }

    @Override
    public String toString() {
        return "Contributor{" +
                "contributorId=" + contributorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", role='" + role + '\'' +
                ", expertise='" + expertise + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}
