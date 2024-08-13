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
    
    public Optional<Student> findByEmailId(String emailId) {
            return getSession()
                    .createNamedQuery("selectByStudentEmailId", Student.class)
                    .setParameter("emailId", emailId)
                    .uniqueResultOptional();
        }

    public Optional<Student> findById(long studentId) {
        Student student = getSession().get(Student.class, studentId);
        return Optional.ofNullable(student);
    }

    public void save(Student student) {
        beginTransaction();
        getSession().save(student);
        commitTransaction();
        closeSession();
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