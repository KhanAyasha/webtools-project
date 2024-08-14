package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.service.ContributorService;
import com.webtools.ayasha.WebProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
@RestController
@RequestMapping("/users")
public class AuthController {
    
    private final StudentService studentService;
    private final ContributorService contributorService;

    @Autowired
    public AuthController(StudentService studentService, ContributorService contributorService) {
        this.studentService = studentService;
        this.contributorService = contributorService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Student newStudent) {
        Optional<Student> existingStudent = studentService.findByEmailId(newStudent.getEmailId());
        if (existingStudent.isPresent()) {
            return ResponseEntity.status(409).body("Email is already registered.");
        }

        studentService.saveStudent(newStudent);
        return ResponseEntity.status(201).body("Registration successful.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student loginRequest) {
        Optional<Student> studentOptional = studentService.findByEmailId(loginRequest.getEmailId());
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (studentService.checkPassword(loginRequest.getPassword(), student.getPassword())) {
                return ResponseEntity.ok("Login successful.");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials.");
            }
        } else {
            return ResponseEntity.status(404).body("Student not found.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Invalidate session or token if using session management
        return ResponseEntity.ok("Logout successful.");
    }

//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody Student student) {
//        Optional<Student> existingStudent = studentService.findByEmailId(student.getEmailId());
//        if (existingStudent.isPresent()) {
//            return ResponseEntity.badRequest().body("Email is already in use.");
//        }
//
//        student.setPassword(passwordEncoder.encode(student.getPassword()));
//        studentService.saveStudent(student);
//        return ResponseEntity.ok("Registration successful.");
//    }
//

}
