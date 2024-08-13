package com.webtools.ayasha.WebProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((requests) -> requests
//                .requestMatchers("/", "/home", "/users/register").permitAll()  // Allow public access to these paths
//                .anyRequest().authenticated()  // All other requests need authentication
//            )
//            .formLogin((form) -> form
//                .loginPage("/login")  // Custom login page URL
//                .loginProcessingUrl("/perform_login")  // URL to submit login form
//                .defaultSuccessUrl("/home", true)  // Redirect after successful login
//                .failureUrl("/login?error=true")  // Redirect if login fails
//                .permitAll()
//            )
//            .logout((logout) -> logout
//                .logoutUrl("/logout")  // URL to trigger logout
//                .logoutSuccessUrl("/home")  // Redirect after logout
//                .permitAll()
//            );
//
//        return http.build();
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
