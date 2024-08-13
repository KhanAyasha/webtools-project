/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.service;

import com.webtools.ayasha.WebProject.dao.SessionDAO;
import com.webtools.ayasha.WebProject.model.StudySession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ayashakhan
 */
 @Component
public class SessionService {
     
     private final SessionDAO sessionDAO;

    @Autowired
    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }
    
    
    // Get all sessions
    public List<StudySession> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

    // Create a new session
    public StudySession createSession(StudySession studySession) {
        // Add any business logic here if needed
        if (studySession.getContributor().getEmailId() == null) {
        throw new IllegalArgumentException("Contributor emailId cannot be null");
    }
        return sessionDAO.createSession(studySession);
    }

    // Update an existing session
    public StudySession updateSession(int id, StudySession studySession) {
        // Add any business logic here if needed
        return sessionDAO.updateSession(id, studySession);
    }

    // Delete a session
    public void deleteSession(int id) {
        // Add any business logic here if needed
        sessionDAO.deleteSession(id);
    }

    // Get sessions by courseId
    public List<StudySession> getSessionsByCourseId(int courseId) {
        return sessionDAO.getSessionsByCourseId(courseId);
    }

    // Get sessions by contributorId
    public List<StudySession> getSessionsByContributorId(int contributorId) {
        return sessionDAO.getSessionsByContributorId(contributorId);
    }

    // Get sessions by studentId
    public List<StudySession> getSessionsByStudentId(int studentId) {
        return sessionDAO.getSessionsByStudentId(studentId);
    }
}
