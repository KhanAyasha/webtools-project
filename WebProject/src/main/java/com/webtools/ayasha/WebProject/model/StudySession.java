/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
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
    
    @ManyToOne
    @JoinColumn(name="courseId", referencedColumnName = "courseId",nullable = false)
    private Courses course;
    
    @ManyToOne
    @JoinColumn(name="contributorId", referencedColumnName = "contributorId",nullable = false)
    private Contributor contributor;
    
    @Column(name="time")
    private LocalTime time;
    
    @Column(name="date")
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name="studentId", referencedColumnName = "studentId", nullable = false)
    private Student student;

    public StudySession() {
    }

    public StudySession(Courses course, Contributor contributor, LocalTime timee, LocalDate date, Student student) {
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
