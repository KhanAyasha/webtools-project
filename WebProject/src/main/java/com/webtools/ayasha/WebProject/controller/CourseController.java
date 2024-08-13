/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.model.Courses;
import com.webtools.ayasha.WebProject.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ayashakhan
 */

@RestController
@RequestMapping("/courses")
public class CourseController {
    
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    
    // API to get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable("id") int courseId) {
        Courses course = courseService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // API to get course by name
    @GetMapping("/name/{courseName}")
    public ResponseEntity<Courses> getCourseByName(@PathVariable("courseName") String courseName) {
        Courses course = courseService.getCourseByName(courseName);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API to get all courses
    @GetMapping("/all")
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    // API to add a new course
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Courses course) {
        courseService.addCourse(course);
        return ResponseEntity.ok("Course added successfully");
    }
    
    // API to update course details
    @PutMapping("/{courseId}")
    public ResponseEntity<Courses> updateCourse(@PathVariable int courseId, @RequestBody Courses course) {
        course.setCourseId(courseId);
        try {
            courseService.updateCourse(course);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // API to delete a course
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable int courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course Deleted Successfully!");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

