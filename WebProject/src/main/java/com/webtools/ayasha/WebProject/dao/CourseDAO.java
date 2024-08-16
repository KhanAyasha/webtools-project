/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.dao;

import com.webtools.ayasha.WebProject.model.Courses;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayashakhan
 */

@Repository
@Qualifier("courseDAO")
public class CourseDAO extends BaseDAO{
    
    public Courses getCourseById(long courseId) {
        Session session = getSession();
        try {
            return session.get(Courses.class, courseId);
        } finally {
//            closeSession();
        }
    }
    
    public List<Courses> getCourseByContributorId(long contributorId) {
        Session session = getSession();
        try {
            // Adjust the query to match your entity structure
            Query<Courses> query = session.createQuery("FROM Courses WHERE contributor.contributorId = :contributorId", Courses.class);
            query.setParameter("contributorId", contributorId);
            return query.list();
        } finally {
            // Close the session if you're managing it manually
            // closeSession();
        }
    }

    
    public List<Courses> getAllCourses() {
        Session session = getSession();
        try {
            Query<Courses> query = session.createQuery("FROM Courses", Courses.class);
            return query.list();
        } 
        finally {
//            closeSession();
        }
    }

    public Courses getCourseByName(String courseName) {
        Session session = getSession();
        try {
            Query<Courses> query = session.createQuery("FROM Courses WHERE courseName = :courseName", Courses.class);
            query.setParameter("courseName", courseName);
            return query.uniqueResult();
        } 
        finally {
//            closeSession();
        }
    }
    
    public void addCourse(Courses course) {
        beginTransaction();
        try {
            getSession().save(course);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }

    public void updateCourse(Courses course) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }
    
    public void deleteCourse(long courseId) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Courses course = session.get(Courses.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }
    
    
    
}
