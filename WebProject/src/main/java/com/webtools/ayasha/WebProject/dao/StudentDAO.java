/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.dao;

import com.webtools.ayasha.WebProject.model.Student;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayashakhan
 */

@Repository
@Qualifier("studentDAO")
public class StudentDAO extends BaseDAO{
    

    public List<Student> getAllStudents() {
//        Session session = getSession();
//        return session.createQuery("FROM Student", Student.class).list();
//        
        
//        beginTransaction();
        Query query = getSession().createQuery("FROM Student", Student.class);
        List<Student> studentList = query.getResultList();
        closeSession();
        return studentList;
    }
    
    public Student findByEmailId(String emailId) {
        if (emailId == null || emailId.trim().isEmpty()) {
            return null; // Handle empty or null email input
        }

        Session session = getSession();
        Transaction transaction = null;
        Student student = null;

        try {
            transaction = session.beginTransaction();
            student = session.createNamedQuery("selectByStudentEmailId", Student.class)
                             .setParameter("emailId", emailId)
                             .uniqueResult();
            transaction.commit();
            System.out.println("transaction completed"+ student.getEmailId());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); // Close the session if manually managed
        }
        System.out.println("student returned");
        return student;
    }


    public Optional<Student> findById(long studentId) {
        Student student = getSession().get(Student.class, studentId);
        return Optional.ofNullable(student);
    }

    public void save(Student student) {
        try {
            beginTransaction();
            getSession().save(student);
            commitTransaction();
            System.out.println("Student saved successfully");
        } catch (Exception e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
            if (getSession().getTransaction() != null) {
                getSession().getTransaction().rollback();
            }
            throw e;
        } finally {
            closeSession();
        }
    }


    public void update(Student student) {
        beginTransaction();
        getSession().update(student);
        commitTransaction();
        closeSession();
    }

    public void delete(Student student) {
        beginTransaction();
        getSession().delete(student);
        commitTransaction();
        closeSession();
    }
    
    
}
