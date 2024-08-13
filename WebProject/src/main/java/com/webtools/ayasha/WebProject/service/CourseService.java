/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.service;

import com.webtools.ayasha.WebProject.dao.CourseDAO;
import com.webtools.ayasha.WebProject.model.Courses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayashakhan
 */
@Component
public class CourseService {
    
    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }
    
    public Courses getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }

    public Courses getCourseByName(String courseName) {
        return courseDAO.getCourseByName(courseName);
    }

    public List<Courses> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public void addCourse(Courses course) {
        courseDAO.addCourse(course);
    }

    public void deleteCourse(int courseId) {
        courseDAO.deleteCourse(courseId);
    }

    public void updateCourse(Courses course) {
        courseDAO.updateCourse(course);
    }

    
}
