<%-- 
    Document   : hometest
    Created on : Oct 1, 2016, 11:00:06 PM
    Author     : MY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/test.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.currentSessionUser.username}!</h1>
        <c:forEach items="${sessionScope.eventList}" var="event">
            <ul>
                <li>Event Name: ${event.eventname}</li>
                <li>Event ID: ${event.eventid}</li>
                <li>Description ${event.description}</li>
                <li>Seats avail ${event.seatsAvail}</li>
                <li>Date Created ${event.dateCreated}</li>
            </ul>
            <a href="SingleEventServlet?eventid=${event.eventid}">View More....</a>
            <img src="${event.imagePath}">
            <p> image path = ${event.imagePath}</p>
        </c:forEach>
            
        
    </body>
</html>
href="${pageContext.request.contextPath}/css/default.css"