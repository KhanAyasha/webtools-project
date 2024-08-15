package com.webtools.ayasha.WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.webtools.ayasha.WebProject"})
public class WebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

        @GetMapping("/")
	public String landing() {
		return "hello";
	}
        
        @GetMapping("/access-denied.htm")
	public String accessDenied() {
            System.out.println("inside access denied");
		return "access-denied";
	}
        
        @GetMapping("/add-course.htm")
	public String addCoursePage() {
		return "add-course";
	}
        
        @GetMapping("/add-course-success.htm")
	public String addCourseSuccessPage() {
		return "add-course-success";
	}
        
        @GetMapping("/my-courses.htm")
	public String myCoursesPage() {
		return "my-courses";
	}
}
