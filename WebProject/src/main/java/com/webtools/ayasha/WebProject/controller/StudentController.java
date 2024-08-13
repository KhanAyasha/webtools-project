/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.service.StudentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getAllStudents() {
        
        return studentService.getAllStudents();
    }
    
    @PutMapping("/update/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable long studentId, @RequestBody Student updatedStudent) {
        // Find the student by ID
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();
            
            // Update the fields
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setMajor(updatedStudent.getMajor());

            // Update the student in the database
            studentService.updateStudent(existingStudent);
            return ResponseEntity.ok("Student updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Student not found.");
        }
    }
    
    
    @GetMapping("/profile/{studentId}")
    public ResponseEntity<Student> getStudentProfile(@PathVariable long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}