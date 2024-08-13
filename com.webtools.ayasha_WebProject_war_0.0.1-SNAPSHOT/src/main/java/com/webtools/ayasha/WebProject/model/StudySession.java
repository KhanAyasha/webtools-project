/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ayashakhan
 */

@Entity
@Table(name = "StudySession")
public class StudySession {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int studySessionID;
    
    @OneToOne(optional=false)
    @JoinColumn(name="courseId", referencedColumnName = "courseId")
    private Courses course;
	
    @OneToOne(optional=false)
    @JoinColumn(name="contributorID", referencedColumnName = "contributorID")
    private Contributor contributor;
	
	
    @Column(name="time")
    private String time;
	
    @Column(name="date")
    private LocalDate date;
	
    @OneToOne(optional=false)
    @JoinColumn(name="studentID", referencedColumnName = "studentID")
    private Student student;

    public StudySession() {
    }

    public StudySession(Courses course, Contributor contributor, String time, LocalDate date, Student student) {
        this.course = course;
        this.contributor = contributor;
        this.time = time;
        this.date = date;
        this.student = student;
    }

    public int getStudySessionID() {
        return studySessionID;
    }

    public void setStudySessionID(int studySessionID) {
        this.studySessionID = studySessionID;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
    
}
