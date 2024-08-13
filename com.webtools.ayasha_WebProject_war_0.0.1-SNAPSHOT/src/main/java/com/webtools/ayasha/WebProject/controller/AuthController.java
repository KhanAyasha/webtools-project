package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//
//@RestController
//@RequestMapping("/users")
public class AuthController {

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
//    // Remove or adjust the login endpoint if using Spring Security’s login
//    @GetMapping("/login")
//    public String login() {
//        // This endpoint might not be necessary if using custom login page with Thymeleaf
//        return "login";  // This should return the name of your login HTML page (e.g., login.html)
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        return ResponseEntity.ok("Logout successful.");
//    }
}
