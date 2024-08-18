/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.controller;

import com.webtools.ayasha.WebProject.dao.ContributorDAO;
import com.webtools.ayasha.WebProject.dao.CourseDAO;
import com.webtools.ayasha.WebProject.dao.SessionDAO;
import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.model.Courses;
import com.webtools.ayasha.WebProject.model.StudySession;
import com.webtools.ayasha.WebProject.service.ContributorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ayashakhan
 */

@Controller
@RequestMapping("/contributor")
public class ContributorController {
    
    private final ContributorDAO contributorDAO;
    private final CourseDAO courseDAO;
    private final SessionDAO sessionDAO;

    @Autowired
    public ContributorController(ContributorDAO contributorDAO,CourseDAO courseDAO,SessionDAO sessionDAO) {
        this.contributorDAO = contributorDAO;
        this.courseDAO =  courseDAO;
        this.sessionDAO = sessionDAO;
    }
    
    @GetMapping("/AllContributors")
    @ResponseBody
    public List<Contributor> getAllContributor() {
        
        return contributorDAO.getAllContributor();
    }
    
    @GetMapping("/{emailId}")
    @ResponseBody
    public ResponseEntity<Contributor> getStudentProfile(@PathVariable String emailId) {
        Contributor conOptional = contributorDAO.findByEmailId(emailId);
        if (conOptional != null) {
            return ResponseEntity.ok(conOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/home.htm")
    public String homePage(HttpSession session, Model model) {
        System.out.println("hit home contributor");
        String role = (String) session.getAttribute("role");
        String emailId = (String) session.getAttribute("emailId");
        
        if ("contributor".equalsIgnoreCase(role)) {
            Contributor contributor = contributorDAO.findByEmailId(emailId); // Fetch contributor details
            if (contributor != null) {
                model.addAttribute("authenticatedUser", contributor);
            }
        }

        model.addAttribute("role", role);
        System.out.println("authenticated contributor");
        return "home";
    }
    
    
    
    @PutMapping("/update/{emailId}")
    public ResponseEntity<String> updateContributor(@PathVariable String emailId, @RequestBody Contributor updatedContributor) {
        // Find the contributor by ID
       Contributor contributor = contributorDAO.findByEmailId(emailId);
        if (contributor != null) {
            Contributor existingContributor = contributor;
            
            // Update the fields
            existingContributor.setFirstName(updatedContributor.getFirstName());
            existingContributor.setLastName(updatedContributor.getLastName());
            existingContributor.setExpertise(updatedContributor.getExpertise());
            existingContributor.setExperienceYears(updatedContributor.getExperienceYears());

            // Update the contributor in the database
            contributorDAO.updateContributor(existingContributor);
            return ResponseEntity.ok("Contributor updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Contributor not found.");
        }
    }
    
    @GetMapping("/add-course.htm/{emailId}")
    public String showAddCourseForm(@PathVariable String emailId,
                                    HttpSession session,
                                    Model model) {

        // Store the emailId in the session if needed
        session.setAttribute("emailId", emailId);

        // Fetch the contributor based on the emailId
        Contributor contributor = contributorDAO.findByEmailId(emailId);

        // Add the contributor to the model if needed
        model.addAttribute("contributor", contributor);

        // Return the view name for adding a course
        return "add-course";
    }

    

    
    @PostMapping("/add-course.htm")
    public String addCourse(@RequestParam String courseName,
                            @RequestParam String description,
                            @RequestParam(required = false) String emailId,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        // If emailId is provided in the request parameter, use it; otherwise, fall back to the session attribute
        if (emailId == null) {
            emailId = (String) session.getAttribute("emailId");
        }

        // Logging to check if emailId is received
        System.out.println("Email ID in addCourse POST: " + emailId);

        if (emailId == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Session expired or email ID not found.");
            return "redirect:/login.htm";
        }

        // Find the contributor by emailId
        Contributor contributor = contributorDAO.findByEmailId(emailId);

        if (contributor == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Contributor not found");
            return "redirect:/contributor/home.htm?emailId=" + emailId;
        }

        // Create and save the course
        Courses course = new Courses();
        course.setContributor(contributor);
        course.setCourseName(courseName);
        course.setDescription(description);

        courseDAO.addCourse(course);

        return "redirect:/contributor/add-course-success.htm";
    }

    
    
    @GetMapping("/logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/login.htm?logout=true";
    }
    
    @GetMapping("/add-course-success.htm")
    public String courseAddSuccess(HttpServletRequest request, HttpServletResponse response){
        return "add-course-success";
    }
    
     
    
    @GetMapping("/my-sessions.htm/{emailId}")
    public String getSessionbyContributorEmail(HttpServletRequest request, @PathVariable String emailId, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Contributor contributor = contributorDAO.findByEmailId(emailId);

            if (contributor == null) {
                model.addAttribute("message", "Contributor not found.");
                return "my-sessions"; // Return the same JSP page with the message
            }

            List<StudySession> studySessions = sessionDAO.getSessionsByContributorId(contributor.getContributorId());

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
    
    
     // Delete a session
    @DeleteMapping("/cancel-session.htm/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable long sessionId) {
        sessionDAO.deleteSession(sessionId);
        return ResponseEntity.ok("Session deleted successfully!");
    }
    

    @GetMapping("/my-courses.htm/{emailId}")
    public String getMyCourses(HttpServletRequest request, @PathVariable String emailId, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Contributor contributor = contributorDAO.findByEmailId(emailId);

            if (contributor == null) {
                model.addAttribute("message", "Contributor not found.");
                return "home"; // Return the same JSP page with the message
            }

            List<Courses> courses = courseDAO.getCourseByContributorId(contributor.getContributorId());

            if (courses == null || courses.isEmpty()) {
                model.addAttribute("message", "No study sessions found.");
            } else {
                model.addAttribute("courses", courses);
            }

            return "my-courses"; // Return the JSP page
        } else {
            model.addAttribute("message", "courses not found.");
            return "my-courses";
        }
    }
    
    // API to delete a course
    @DeleteMapping("/delete-course.htm/{courseId}")
    public ResponseEntity<String> deleteCourse(HttpServletRequest request,@PathVariable long courseId) {
        try {
            courseDAO.deleteCourse(courseId);
            return ResponseEntity.ok("Course Deleted Successfully!");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    // API to add a new course
//    @PostMapping("/add-course.htm")
//    public ResponseEntity<String> addCourse( HttpServletRequest request,@RequestBody Courses course) {
//        HttpSession session = request.getSession();
//        if(session != null){
//            courseDAO.addCourse(course);
//            return ResponseEntity.ok("Course added successfully");
//        }else{
//            return ResponseEntity.ok().body("You are logged out.");
//        }
//
//        
//    }   
    

    
    
}
