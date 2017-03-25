
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>reset password</h1>
        <form action="ResetPasswordServlet" method="POST">
            Enter new password:
            <input type="password" name="newPassword"/>
            <br/>
            Re Enter new Password:
            <input type="password"/>
            <br/>
            <input type="submit" value ="Reset"/>
        </form>
    </body>
</html>
