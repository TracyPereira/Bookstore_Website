
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <c:set var="event" value="${requestScope.singleEvent}"></c:set>
    </head>
    <style>
        img {
            width: 400px; height: 400px;
        }
    </style>
    <body>
        
        <h1>Single Event .jsp</h1>
        
       
        <h2>NAme: ${event.eventname}</h2>
        <img src="${event.imagePath}"/>
        
    </body>
</html>
