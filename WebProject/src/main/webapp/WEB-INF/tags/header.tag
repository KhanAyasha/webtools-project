<%-- 
    Document   : header
    Created on : Aug 15, 2024, 5:31:15 PM
    Author     : ayashakhan
--%>

<%@ tag description="Custom header tag for navigation menu" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    nav {
        background-color: black;
        padding: 10px 20px;
    }

    nav ul {
        display: flex;
        justify-content: space-around;
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    nav ul li {
        margin: 0 15px;
    }

    nav ul li a {
        color: whitesmoke;
        text-decoration: none;
        font-weight: bold;
        padding: 8px 16px;
        border-radius: 4px;
        transition: background-color 0.3s ease;
    }

    nav ul li a:hover {
        background-color: #0056b3;
    }
</style>

<nav>
    <ul>
        <c:set var="role" value="${sessionScope.role}"/>
        
        
        <c:if test="${role eq 'student'}">
            <li><a href="${pageContext.request.contextPath}/student/home.htm">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/student/my-sessions.htm/${sessionScope.emailId}">View Sessions</a></li>
            <li><a href="${pageContext.request.contextPath}/student/view-courses.htm">View Courses</a></li>
            <li><a href="${pageContext.request.contextPath}/student/add-session.htm/${sessionScope.emailId}">Schedule a session</a></li>
        </c:if>
        
        <c:if test="${role eq 'contributor'}">
            <li><a href="${pageContext.request.contextPath}/contributor/home.htm">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/contributor/my-sessions.htm/${sessionScope.emailId}">View Sessions</a></li>
            <li><a href="${pageContext.request.contextPath}/contributor/add-course.htm/${sessionScope.emailId}">Add Courses</a></li>
            <li><a href="${pageContext.request.contextPath}/contributor/my-courses.htm/${sessionScope.emailId}">My Courses</a></li>
        </c:if>
        
        <li><a href="${pageContext.request.contextPath}/logout.htm">Logout</a></li>
    </ul>
</nav>
