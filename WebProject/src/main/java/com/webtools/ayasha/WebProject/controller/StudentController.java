/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.dao.CourseDAO;
import com.webtools.ayasha.WebProject.dao.SessionDAO;
import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;
    private final SessionDAO sessionDAO;

    @Autowired
    public StudentController(StudentDAO studentDAO, CourseDAO courseDAO,SessionDAO sessionDAO ) {
        this.studentDAO = studentDAO;
        this.sessionDAO = sessionDAO;
        this.courseDAO = courseDAO;
        
    }

    @GetMapping("/allStudents")
    @ResponseBody
    public List<Student> getAllStudents() {
        
        return studentDAO.getAllStudents();
    }
    
    @GetMapping("/home.htm")
    public String homePage(HttpSession session, Model model) {
        System.out.println("hit home student");
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        return "home";
    }

    
    @PutMapping("/update/{emailId}")
    public ResponseEntity<String> updateStudent(@PathVariable String emailId, @RequestBody Student updatedStudent) {
        // Find the student by ID
        Student student = studentDAO.findByEmailId(emailId);
        if (student != null) {
            Student existingStudent = student;
            
            // Update the fields
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setMajor(updatedStudent.getMajor());
            

            // Update the student in the database
            studentDAO.updateStudent(existingStudent);
            return ResponseEntity.ok("Student updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Student not found.");
        }
    }
    
    
    @GetMapping("/{emailId}")
    @ResponseBody
    public ResponseEntity<Student> getStudentProfile(@PathVariable String emailId) {
        Student studentOptional = studentDAO.findByEmailId(emailId);
        if (studentOptional != null) {
            return ResponseEntity.ok(studentOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/login.htm?logout=true";
    }
    
}