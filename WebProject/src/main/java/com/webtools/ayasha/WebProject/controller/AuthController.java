package com.webtools.ayasha.WebProject.controller;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import com.webtools.ayasha.WebProject.dao.ContributorDAO;
import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.model.RegisterRequest;
import com.webtools.ayasha.WebProject.model.Student;
import com.webtools.ayasha.WebProject.service.ContributorService;
import com.webtools.ayasha.WebProject.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//
@Controller
@RequestMapping("/")
public class AuthController {
    
    private final StudentDAO studentDAO;
    private final ContributorDAO contributorDAO;

    @Autowired
    public AuthController(StudentDAO studentDAO, ContributorDAO contributorDAO) {
        this.studentDAO = studentDAO;
        this.contributorDAO = contributorDAO;
    }
    
    
    @GetMapping("/login.htm")
    public String loginPage(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            if (null != httpSession.getAttribute("role")) {
               if (httpSession.getAttribute("role").equals("student")) {
                    return "redirect:/student-home.htm";
                } else if (httpSession.getAttribute("role").equals("student")){
                    return "redirect:/contributor-home.htm";
                }
            }//role
        
        }//httpsession
        return "login";
    }
        
    
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody Student newStudent) {
//        Optional<Student> existingStudent = studentService.findByEmailId(newStudent.getEmailId());
//        if (existingStudent.isPresent()) {
//            return ResponseEntity.status(409).body("Email is already registered.");
//        }
//
//        studentService.saveStudent(newStudent);
//        return ResponseEntity.status(201).body("Registration successful.");
//    }
    
    

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Student loginRequest) {
//        Optional<Student> studentOptional = studentService.findByEmailId(loginRequest.getEmailId());
//        if (studentOptional.isPresent()) {
//            Student student = studentOptional.get();
//            if (studentService.checkPassword(loginRequest.getPassword(), student.getPassword())) {
//                return ResponseEntity.ok("Login successful.");
//            } else {
//                return ResponseEntity.status(401).body("Invalid credentials.");
//            }
//        } else {
//            return ResponseEntity.status(404).body("Student not found.");
//        }
//    }

    @GetMapping("/register.htm")
    public String registerPage(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("student")) {
                    return "redirect:/student-home.htm";
                } else if (httpSession.getAttribute("role").equals("contributor")) {
                    return "redirect:/contributor-home.htm";
                }
            }
        }
        return "register"; // Return the registration JSP page
    }

    // POST API to handle registration
    @PostMapping("/register.htm")
    public String register(@RequestBody RegisterRequest requestBody, HttpServletRequest request) {

        System.out.println("Register endpoint hit");
        HttpSession session = request.getSession();
        String role = requestBody.getRole();

        if ("student".equalsIgnoreCase(role)) {
            // Check if the student already exists
            Student existingStudent = studentDAO.findByEmailId(requestBody.getEmailId());
            if (existingStudent != null) {
                return "redirect:/register.htm?error=Email already registered as student";
            }

            // Create a new student and save
            Student student = new Student();
            student.setFirstName(requestBody.getFirstName());
            student.setLastName(requestBody.getLastName());
            student.setEmailId(requestBody.getEmailId());
            student.setPassword(requestBody.getPassword());
            student.setMajor(requestBody.getMajor());
            studentDAO.save(student);

            // Set session attribute and redirect to student home page
            session.invalidate();
            return "redirect:/register-Success.htm";

        } else if ("contributor".equalsIgnoreCase(role)) {
            // Check if the contributor already exists
            Contributor existingContributor = contributorDAO.findByEmailId(requestBody.getEmailId());
            if (existingContributor != null) {
                return "redirect:/register.htm?error=Email already registered as contributor";
            }

            // Create a new contributor and save
            Contributor contributor = new Contributor();
            contributor.setFirstName(requestBody.getFirstName());
            contributor.setLastName(requestBody.getLastName());
            contributor.setEmailId(requestBody.getEmailId());
            contributor.setPassword(requestBody.getPassword());
            int experienceYears = (requestBody.getExperienceYears() != null) ? requestBody.getExperienceYears() : 0;
            contributor.setExperienceYears(experienceYears);

            contributor.setExpertise(requestBody.getExpertise());
//            contributor.setExperienceYears(requestBody.getExperienceYears());
            contributorDAO.save(contributor);

            session.invalidate();
            return "redirect:/register-Success.htm";

        } else {
            // Invalid role
            System.out.println("You did not register");
            return "redirect:/register.htm?error=Invalid role selected";
        }
    }

        
    @GetMapping("/register-Success.htm")
    public String registrationSuccessPage() {
	return "registration-success";
    }

    @GetMapping("/logout.htm")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/login.htm?logout=true";
    }
//   
    
    private Object checkCredentialsAndGetUser(String email, String password, String role) {
        
        Student student = null;
        Contributor contributor = null;
        
        boolean verified = false;
        
        if(role == null)
            return null;
        
        
        if(role.equals("student")){    
            student = studentDAO.findByEmailId(email); 
            if(student == null)
                return null;
            verified = (email.equals(student.getEmailId()) && password.equals(student.getPassword()));
        }else if(role.equals("contributor")){
            if(contributor == null)
                return null;
            contributor = contributorDAO.findByEmailId(email);
            verified = (email.equals(contributor.getEmailId()) && password.equals(contributor.getPassword()));
        }
        
        if(verified) {
            if(student != null)
                return student;
            else return contributor;
                       
        }
        return null;
    }

}
