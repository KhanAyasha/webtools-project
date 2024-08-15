/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.service;

/**
 *
 * @author ayashakhan
 */
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RoleBasedAccessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            String role = (String) session.getAttribute("role");
            String requestURI = request.getRequestURI();

            System.out.println("Role: " + role);
            System.out.println("Request URI: " + requestURI);

            if (requestURI.contains("/contributor") && !"contributor".equalsIgnoreCase(role)) {
                System.out.println("Access denied to contributor URL");
                response.sendRedirect("/WebProject/access-denied.htm");
                return;
            }

            if (requestURI.contains("/student/") && !"student".equalsIgnoreCase(role)) {
                System.out.println("Access denied to student URL");
                response.sendRedirect("/WebProject/access-denied.htm");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
