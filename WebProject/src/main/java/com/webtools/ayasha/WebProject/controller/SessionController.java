/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.dao.SessionDAO;
import com.webtools.ayasha.WebProject.model.StudySession;
import com.webtools.ayasha.WebProject.service.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ayashakhan
 */


@RestController
@RequestMapping("/sessions")
public class SessionController {
    
    private final SessionDAO sessionDAO;

    @Autowired
    public SessionController(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }
    
    
    // Get all sessions
    @GetMapping
    public ResponseEntity<List<StudySession>> getAllSessions() {
        List<StudySession> sessions = sessionDAO.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    // Create a new session
    @PostMapping
    public ResponseEntity<StudySession> createSession(@RequestBody StudySession session) {
        StudySession createdSession = sessionDAO.createSession(session);
        return ResponseEntity.ok(createdSession);
    }

    // Update an existing session
    @PutMapping("/{id}")
    public ResponseEntity<StudySession> updateSession(@PathVariable int id, @RequestBody StudySession session) {
        StudySession updatedSession = sessionDAO.updateSession(id, session);
        return updatedSession != null ? ResponseEntity.ok(updatedSession) : ResponseEntity.notFound().build();
    }

   

    // View sessions by courseId
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudySession>> getSessionsByCourseId(@PathVariable int courseId) {
        List<StudySession> sessions = sessionDAO.getSessionsByCourseId(courseId);
        return ResponseEntity.ok(sessions);
    }

    // View sessions by contributorId
    @GetMapping("/contributor/{contributorId}")
    public ResponseEntity<List<StudySession>> getSessionsByContributorId(@PathVariable int contributorId) {
        List<StudySession> sessions = sessionDAO.getSessionsByContributorId(contributorId);
        return ResponseEntity.ok(sessions);
    }

    // View sessions by studentId
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudySession>> getSessionsByStudentId(@PathVariable int studentId) {
        List<StudySession> sessions = sessionDAO.getSessionsByStudentId(studentId);
        return ResponseEntity.ok(sessions);
    }
}
