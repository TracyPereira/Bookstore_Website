<%-- 
    Document   : index
    Created on : Sep 26, 2016, 8:14:35 PM
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
        <c:if test="${pageContext.response.containsHeader('isValid') && (pageContext.response.getHeader('isValid') == false)}"> 
            <script>alert("invalid Login");</script>
        </c:if>
    </head>
    <body>
         <body>
        <div>Login</div>
        <form name = "loginform" action="LoginServlet" method="POST">
            Username: <input type ="text" name="un"/>
            <br/>
            Password: <input type ="password" name="pw"/>
            <input type="submit" value="Submit"/>
            <% //System.out.println("helloo"); %>
            <br/>
        </form>
            <%  
                out.println(response.containsHeader("isValid"));
                if(response.containsHeader("isValid") && response.getHeader("isValid") == "false") { 
                //REPLACE THIS WITH JSTL --> THIS IS TERRIBLE AAAARGH
                // how?
                //THIS CAN BE DONE REFER REGISTER.JSP
                System.out.println("isValid == false");
                out.println("invalid login");
             } %>
           <br/>
             <a href="forgotpassword.jsp">Forgot Password?</a>
             <a href="register.jsp">Register?</a>
    </body>
    
</html>
