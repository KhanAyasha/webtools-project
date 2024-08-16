/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.dao;

import com.webtools.ayasha.WebProject.model.StudySession;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ayashakhan
 */

@Repository
@Qualifier("sessionDAO")
public class SessionDAO extends BaseDAO{
    
    // Get all sessions
    public List<StudySession> getAllSessions() {
        Session session = getSession();
        try {
            Query<StudySession> query = session.createQuery("from StudySession", StudySession.class);
            return query.getResultList();
        } 
        finally {
//            closeSession();
        }
    }

    // Create a new session
    public StudySession createSession(StudySession studySession) {
        Session session = getSession();
        try {
            beginTransaction();
            session.save(studySession);
            commitTransaction();
            return studySession;
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }

    // Update an existing session
    public StudySession updateSession(int id, StudySession studySession) {
        Session session = getSession();
        try {
            beginTransaction();
            StudySession existingSession = session.get(StudySession.class, id);
            if (existingSession != null) {
                existingSession.setCourse(studySession.getCourse());
                existingSession.setContributor(studySession.getContributor());
                existingSession.setTime(studySession.getTime());
                existingSession.setDate(studySession.getDate());
                existingSession.setStudent(studySession.getStudent());
                session.update(existingSession);
                commitTransaction();
                return existingSession;
            } else {
                return null; // or throw an exception
            }
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }

    // Delete a session
    public void deleteSession(int id) {
        Session session = getSession();
        try {
            beginTransaction();
            StudySession sessionToDelete = session.get(StudySession.class, id);
            if (sessionToDelete != null) {
                session.delete(sessionToDelete);
                commitTransaction();
            }
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } 
//        finally {
//            closeSession();
//        }
    }

    // Get sessions by courseId
    public List<StudySession> getSessionsByCourseId(int courseId) {
        Session session = getSession();
        try {
            Query<StudySession> query = session.createQuery(
                "from StudySession where course.courseId = :courseId", StudySession.class);
            query.setParameter("courseId", courseId);
            return query.getResultList();
        } 
        finally {
//            closeSession();
        }
    }

    // Get sessions by contributorId
    public List<StudySession> getSessionsByContributorId(int contributorId) {
        Session session = getSession();
        try {
            Query<StudySession> query = session.createQuery(
                "from StudySession where contributor.contributorID = :contributorId", StudySession.class);
            query.setParameter("contributorId", contributorId);
            return query.getResultList();
        } 
        finally {
//            closeSession();
        }
    }

    // Get sessions by studentId
    public List<StudySession> getSessionsByStudentId(long studentId) {
        Session session = getSession();
        try {
            Query<StudySession> query = session.createQuery(
                "from StudySession where student.studentId = :studentId", StudySession.class);
            query.setParameter("studentId", studentId);
            return query.getResultList();
        } finally {
//            closeSession();
        }
    }
    
    public void saveStudySession(StudySession session) {
        try {
            getSession();
            beginTransaction();
            getSession().save(session);
            commitTransaction();
            System.out.println("Session saved successfully");
        } catch (Exception e) {
            System.err.println("Error saving session: " + e.getMessage());
            e.printStackTrace();
            if (getSession().getTransaction() != null) {
                getSession().getTransaction().rollback();
            }
            throw e;
        } 
        finally {
//            closeSession(); // Ensure the session is closed in the finally block
        }
    }

    
}
