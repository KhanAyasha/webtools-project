package com.webtools.ayasha.WebProject.controller;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import com.webtools.ayasha.WebProject.dao.ContributorDAO;
import com.webtools.ayasha.WebProject.dao.StudentDAO;
import com.webtools.ayasha.WebProject.model.Contributor;
import com.webtools.ayasha.WebProject.model.LoginRequest;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(StudentDAO studentDAO, ContributorDAO contributorDAO) {
        this.studentDAO = studentDAO;
        this.contributorDAO = contributorDAO;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Initialize the encoder
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
        
    @PostMapping("/login.htm")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("Login endpoint hit");
        HttpSession httpSession = request.getSession();
        String emailId = loginRequest.getEmailId();
        System.out.println("email id taken"+emailId);
        String password = loginRequest.getPassword();
        String role = loginRequest.getRole();

        // Use the existing method to check credentials and get the user
        Object user = checkCredentialsAndGetUser(emailId, password, role);

        if (user != null) {
            // Set session attributes based on role
            System.out.println("I got the user"+user.toString());
            if ("student".equalsIgnoreCase(role)) {
                httpSession.setAttribute("role", "student");
                httpSession.setAttribute("emailId", emailId);
                return "redirect:/student-home.htm";
            } else if ("contributor".equalsIgnoreCase(role)) {
                httpSession.setAttribute("role", "contributor");
                httpSession.setAttribute("emailId", emailId);
                return "redirect:/contributor-home.htm";
            }
        }
        System.out.println("You got some error");
        // If credentials are invalid
        return "redirect:/login.htm?error=Invalid credentials or role";
    }



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
            student.setPassword(passwordEncoder.encode(requestBody.getPassword()));
//            student.setPassword(requestBody.getPassword());
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
            contributor.setPassword(passwordEncoder.encode(requestBody.getPassword()));
//            contributor.setPassword(requestBody.getPassword());
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
    
    public Object checkCredentialsAndGetUser(String emailId, String password, String role) {
        if (role == null) return null;
        System.out.println(emailId);

        if (role.equals("student")) {    
            Student student = studentDAO.findByEmailId(emailId);
            if (student == null) {
                System.out.println("student is still null");
                return null;
            }
            System.out.println("student password " + student.getPassword() );
            boolean verified = emailId.equals(student.getEmailId()) && passwordEncoder.matches(password, student.getPassword());
            return verified ? student : null;

        } else if (role.equals("contributor")) {
            Contributor contributor = contributorDAO.findByEmailId(emailId);
            if (contributor == null) return null;
            System.out.println("contribuot password " + contributor.getPassword() );
            boolean verified = emailId.equals(contributor.getEmailId()) && passwordEncoder.matches(password, contributor.getPassword());
            return verified ? contributor : null;
        }

        return null;
    }


}
