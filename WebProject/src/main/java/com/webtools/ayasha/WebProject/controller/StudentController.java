/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.dao.ContributorDAO;
import com.webtools.ayasha.WebProject.dao.CourseDAO;
import com.webtools.ayasha.WebProject.dao.SessionDAO;
import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.model.Courses;
import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.model.StudySession;
import com.webtools.ayasha.WebProject.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final ContributorDAO contributorDAO;

    @Autowired
    public StudentController(StudentDAO studentDAO, CourseDAO courseDAO,SessionDAO sessionDAO, ContributorDAO contributorDAO ) {
        this.studentDAO = studentDAO;
        this.sessionDAO = sessionDAO;
        this.courseDAO = courseDAO;
        this.contributorDAO = contributorDAO;
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
        String emailId = (String) session.getAttribute("emailId");

        if ("student".equalsIgnoreCase(role)) {
            Student student = studentDAO.findByEmailId(emailId); // Fetch student details
            if (student != null) {
                model.addAttribute("authenticatedUser", student);
            }
        } 

        model.addAttribute("role", role);
        return "home";
    }

    
    @PutMapping("/update/{emailId}")
    public String updateStudent(HttpServletRequest request,@PathVariable String emailId, @RequestBody Student updatedStudent) {
        // Find the student by ID
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            Student student = studentDAO.findByEmailId(emailId);
            if (student != null) {
                Student existingStudent = student;

                // Update the fields
                existingStudent.setFirstName(updatedStudent.getFirstName());
                existingStudent.setLastName(updatedStudent.getLastName());
                existingStudent.setMajor(updatedStudent.getMajor());


                // Update the student in the database
                studentDAO.updateStudent(existingStudent);
                return "Student updated successfully.";
            } else {
                return "Student not found.";
            }
        }
        return "redirect:/login.htm?logout=true";
}   
    
    
    
    @GetMapping("/{emailId}")
    @ResponseBody
    public ResponseEntity<Student> getStudentProfile(HttpServletRequest request,@PathVariable String emailId) {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            Student studentOptional = studentDAO.findByEmailId(emailId);
            if (studentOptional != null) {
                return ResponseEntity.ok(studentOptional);
            } else {
                return ResponseEntity.notFound().build();
            }
        }else{
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
    


    @GetMapping("/my-sessions.htm/{emailId}")
    public String getSessionbyStudentEmail(HttpServletRequest request, @PathVariable String emailId, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Student student = studentDAO.findByEmailId(emailId);

            if (student == null) {
                model.addAttribute("message", "Student not found.");
                return "my-sessions"; // Return the same JSP page with the message
            }

            List<StudySession> studySessions = sessionDAO.getSessionsByStudentId(student.getStudentId());

            if (studySessions == null || studySessions.isEmpty()) {
                model.addAttribute("message", "No study sessions found.");
            } else {
                model.addAttribute("studySessions", studySessions);
            }

            return "my-sessions"; // Return the JSP page
        } else {
            model.addAttribute("message", "Session not found.");
            return "my-sessions";
        }
    }
    
    
    @GetMapping("/view-courses.htm")
    public String getCoursesForStudent(Model model) {
        List<Courses> courses = courseDAO.getAllCourses(); 
        model.addAttribute("courses", courses);
        return "my-courses"; 
    }
    
    @PostMapping("schedule-session.htm/{emailId}/{courseId}")
    public ResponseEntity<String> bookSessionbyId(@PathVariable String emailId, @PathVariable int courseId){
        System.out.println("inside schedule session");

        // Step 1: Fetch the student by email
        Student student = studentDAO.findByEmailId(emailId);
        if (student == null) {
            System.out.println("study student null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        
        // Step 2: Fetch the course by ID
        Courses course = courseDAO.getCourseById(courseId);
        if (course == null) {
            System.out.println("study course null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

        
        // Step 2: Fetch the contributor by ID
        Contributor contributor = course.getContributor();
        if (contributor == null) {
            System.out.println("study conti null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contributor not found");
        }

        // Step 4: Create and save the new session
        StudySession session = new StudySession();
        session.setStudent(student);
        session.setContributor(contributor);
        session.setCourse(course);
        session.setDate(LocalDate.now()); // Set session date, you can customize this
        session.setTime(LocalTime.now());
        System.out.println("object created");
        sessionDAO.createSession(session);
        System.out.println("object created");
        return ResponseEntity.status(HttpStatus.CREATED).body("Session booked successfully");
    }
    
    
    
    @GetMapping("/add-session.htm/{emailId}")
    public String showAddCourseForm(@PathVariable String emailId,
                                    HttpSession session,
                                    Model model) {

        // Store the emailId in the session if needed
        session.setAttribute("emailId", emailId);

        // Fetch the contributor based on the emailId
        Student student = studentDAO.findByEmailId(emailId);

        // Add the contributor to the model if needed
        model.addAttribute("student", student);

        // Return the view name for adding a course
        return "add-session";
    }
    
    
}