/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ayashakhan
 */
//@Slf4j
@Component
public class CustomInterceptor implements HandlerInterceptor{

    private final Map<String, String[]> studentRoutes;
    private final Map<String, String[]> contributorRoutes;
    private final Map<String, String[]> unauthenticatedRoutes;
    
    public CustomInterceptor() {
        unauthenticatedRoutes = Map.of(
                "/", new String[]{"GET"},
                "/login.htm", new String[]{"GET", "POST"},
                "/register.htm", new String[]{"GET", "POST"},
                "/access-denied.htm", new String[]{"GET"},
                "/register-Success.htm", new String[]{"GET"},
                "/images/background.jpg", new String[]{"GET","POST"},
                "/student/*", new String[]{"GET","POST"},
                "/contributor/*", new String[]{"GET","POST"}
                
                
                
        );
        studentRoutes = Map.of(
                "/student/.", new String[]{"GET", "POST"},
//                "/student/.*", new String[]{"GET", "POST"},
                "/login.htm", new String[]{"GET", "POST"},
                "/logout.htm", new String[]{"GET"},
                "/register.htm", new String[]{"GET", "POST"},
                "/access-denied.htm", new String[]{"GET"},
                "/images/background.jpg", new String[]{"GET","POST"},
                "/student/*/{emailId}",new String[]{"GET","POST"}
//                "/student/*", new String[]{"GET","POST"}
//                
        );
        contributorRoutes = Map.of(
                "/student/.", new String[]{"GET", "POST"},
//                "/contributor/.*", new String[]{"GET"},
                "/contributor/submit", new String[]{"POST"},
                "/login.htm", new String[]{"GET", "POST"},
                "/logout.htm", new String[]{"GET"},
                "/register.htm", new String[]{"GET", "POST"},
                "/access-denied.htm", new String[]{"GET"},
                "/images/background.jpg", new String[]{"GET","POST"}
//                "/contributor/*", new String[]{"GET","POST"}
        );
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("Request and Response is complete");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Called");
    }

    
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("Pre Handle method is Called");        
//        String servletPath = request.getServletPath();
//        String method = request.getMethod();
//
//        HttpSession session = request.getSession(false);
//        String role = "unauthenticated";
//        if (session != null && session.getAttribute("role") != null) {
//            role = (String) session.getAttribute("role");
//        }
//        System.out.println("role "+ role );
//        Map<String, String[]> authorizedRoutes = getAuthorizedRoutes(role);
//
//        String allowedPath = checkServletPathAllowedAndReturnAllowedPath(servletPath, authorizedRoutes.keySet());
//        if (allowedPath != null && isMethodAllowed(method, authorizedRoutes.get(allowedPath))) {
//            return true;
//        }
//
//        response.sendRedirect(request.getContextPath() + "/logout.htm");
//        return false;
//    }

    private Map<String, String[]> getAuthorizedRoutes(String role) {
        return switch (role) {
            case "student" -> studentRoutes;
            case "contributor" -> contributorRoutes;
            default -> unauthenticatedRoutes;
        };
    }

    private String checkServletPathAllowedAndReturnAllowedPath(String servletPath, Set<String> allowedPaths) {
        for (String allowedPath : allowedPaths) {
            if (isServletPathPatternMatching(servletPath, allowedPath)) {
                return allowedPath;
            }
        }
        return null;
    }

    private boolean isMethodAllowed(String method, String[] allowedMethods) {
        for (String allowedMethod : allowedMethods) {
            if (method.equalsIgnoreCase(allowedMethod)) {
                return true;
            }
        }
        return false;
    }

    private boolean isServletPathPatternMatching(String servletPath, String pattern) {
        String[] servletPathParts = servletPath.split("/");
        String[] pathParts = pattern.split("/");
        if (servletPathParts.length != pathParts.length) {
            return false;
        }
        for (int i = 0; i < servletPathParts.length; i++) {
            if (!pathParts[i].equals("*") && !pathParts[i].equals(servletPathParts[i])) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
