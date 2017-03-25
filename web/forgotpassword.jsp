<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery.js" type="text/javascript"></script>
        <script>
            function sendEmail() {
                console.log("sendEmail();");
                var username = $("#username").val();
                console.log("Username = " + username);
                if (username === "")
                    $("#userinput").val("Enter a username");
                else {
                    $.post("ForgotPasswordServlet", {username: username, action: "forgotPassword"},
                                function (data, status, xhr) {
                                   console.log("data: " + data) 
                                   if(data == "true") {
                                       console.log("Email sent.");
                                   } else {
                                       console.log("data is not true.")
                                      // alert(data);
                                   }
                                });

                    }
                }/*
            function resetPassword() {
                var username = $("#username").val();
                var otp = $("#otp").val();
                console.log("resetPassword();!  username: "+ username+ " otp: "+otp);
                if (username === "")
                    $("#userinput").val("Enter a username");
                else {
                    $.post("ForgotPasswordServlet", {username: username, action: "checkOTP", otp: otp});
                }
            }*/
    //DONT LET USERINPUT BE EMPTY USING FRONT END
        </script>
    </head>
    <body>
        <div>
            <form name="forgotpassform" action="ForgotPasswordServlet" method="POST">
                <input type="hidden" value="" name="action"/>
                You seem to have forgotten your password.
                <br/>
                Please enter your username in the field below.

                <input type="text" name="username" id="username"/>
                <br/>

                <br/>
                We shall send you email with an OTP. 
                <br/>
                <input type="button" onclick="sendEmail();" value="Send Email">
                <br/>
                Please enter the OTP here to reset your password.
                <input type="number" name="otp" id="otp"/>
                <br/>
                <input type="button" value="Reset Password" onclick="form.submit();">
            </form>    
        </div>
    </body>
</html>
