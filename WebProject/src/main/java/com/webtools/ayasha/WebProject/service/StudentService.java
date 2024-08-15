/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.service;

import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Student;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayashakhan
 */
 @Component
public class StudentService {
    
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    
    public Optional<Student> findById(long studentId) {
        return studentDAO.findById(studentId);
    }

    public Student findByEmailId(String emailId) {
        return studentDAO.findByEmailId(emailId);
    }

    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    public void deleteStudent(Student student) {
        studentDAO.delete(student);
    }
    
    public boolean checkPassword(String rawPassword, String storedPassword) {
        // Plain text comparison
        return rawPassword.equals(storedPassword);
    }
}

    

