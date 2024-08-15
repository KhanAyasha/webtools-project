package com.webtools.ayasha.WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication(scanBasePackages = {"com.webtools.ayasha.WebProject"})
public class WebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

        @GetMapping("/")
	public String landing() {
		return "hello";
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
