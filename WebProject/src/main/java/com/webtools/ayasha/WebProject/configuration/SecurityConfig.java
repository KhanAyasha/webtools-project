package com.webtools.ayasha.WebProject.configuration;

import com.webtools.ayasha.WebProject.service.RoleBasedAccessFilter;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .anyRequest().permitAll() // Allow all requests
//            .and()
//            .csrf().disable(); // Disable CSRF protection
//
//        return http.build();
//    }
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RoleBasedAccessFilter roleBasedAccessFilter) throws Exception {
        http
            .addFilterBefore(roleBasedAccessFilter, UsernamePasswordAuthenticationFilter.class) // Add the custom filter
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/register.htm", "/logout.htm","/access-denied.htm").permitAll() // Allow access to these pages
                .requestMatchers("/student/**").authenticated() // Ensure the user is authenticated
                .requestMatchers("/contributor/**").authenticated() // Ensure the user is authenticated
                .anyRequest().authenticated() // Any other request requires authentication
            )
            .formLogin((form) -> form
                .loginPage("/login.htm") // Custom login page
                .permitAll() // Allow access to the login page
                .defaultSuccessUrl("/", true) // Redirect to home page after successful login
            )
            .logout((logout) -> logout
                .logoutUrl("/logout.htm")
                .logoutSuccessUrl("/login.htm") // Redirect to login page after logout
                .permitAll()
            )
            .csrf().disable() // Disable CSRF protection for simplicity
            .exceptionHandling()
                .accessDeniedPage("/WebProject/access-denied.htm");

        return http.build();
    }




}
