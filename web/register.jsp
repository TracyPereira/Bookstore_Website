<%-- 
    Document   : register
    Created on : Sep 27, 2016, 7:00:29 PM
    Author     : MY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/test.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.js" type="text/javascript"></script>
        <title>register.jsp</title>
        <link rel="stylesheet" type="text/css" href="css/jquery.realperson.css"> 
        <script type="text/javascript" src="js/jquery.plugin.min.js"></script> 
        <script src="js/reg.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/jquery.realperson.min.js"></script>
        <c:if test="${pageContext.response.containsHeader('isHuman') && (pageContext.response.getHeader('isHuman') == false)}"> 
            <script>alert("captcha failed!!!!!!!!!");</script>
        </c:if>
    </head>
    <body> 
        <h1>Hello World! register.jsp</h1>
        <br/> 
        isHuman: ${pageContext.response.getHeader("isHuman")}
        <form name="regform" method="POST" action="RegisterServlet">
            Username: <input id="userinput" type="text" name="username"/><span id="userspan"></span>
            <br/>
            Password: <input type="password" name="password"/>
            <br/>
            First Name: <input  type="text" name="firstname"/>
            <br/>
            Last Name: <input type="text" name="lastname"/>
            <br/>
            DOB: <input type="date" name="dob"/>
            <br/>
            Email: <input type="text" name="email"/>
            <br/>
            Phone: <input type="tel" name="phone"/>
            <br/>
            Sex:
            Male:<input type="radio" name="sex" value="male">
            Female:<input type="radio" name="sex" value="female">
            <br/>
            Preferences:
            <input type="checkbox" name="preference" value="food">Food<br/>
            <input type="checkbox" name="preference" value="music">Music<br/>
            <input type="checkbox" name="preference" value="sports">Sports<br/>
            <input type="checkbox" name="preference" value="art">Art<br/>
            <input type="checkbox" name="preference" value="nature">Nature<br/>   
            <br/>
            <input type='text' id="realPerson" name="realPerson"/>
            <br/>

            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
