/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

/**
 *
 * @author ayashakhan
 */

@Entity
@Table(name = "Student", uniqueConstraints = @UniqueConstraint(columnNames = { "emailId"}))
@NamedQueries({
        @NamedQuery(name = "selectByStudentEmailId", query = "FROM Student WHERE emailId = :emailId")
})
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long studentId;
    
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
    @Check(constraints = "role = 'student'")
    private String role = "student";
    
    @Column
    private String major;

    public Student() {}

    public Student(String firstName, String lastName, String password, String emailId, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.major = major;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", role='" + role + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
