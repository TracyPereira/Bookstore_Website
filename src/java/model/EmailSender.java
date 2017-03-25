package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.beans.UserBean;

public class EmailSender {

    private static final String SENDING_EMAIL = "jpstarmad@gmail.com";
    private static final String PASSWORD = "blueandorange";

    public static void sendEmail(UserBean user, emailType type) {

        
        String to = user.getEmail();
        // Sender's email ID needs to be mentioned
        //String from = SENDING_EMAIL;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDING_EMAIL, PASSWORD);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(SENDING_EMAIL));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            if (type == emailType.REGISTER) {
                // Set Subject: header field
                message.setSubject("Hobbyists.com Confirmation Email");
                // Now set the actual message
                message.setText("Your confirmation link : "
                        + "http://localhost:8080/WTmainFound/ConfirmRegistrationServlet?vcode="
                        + user.getVcode() + "&username=" + user.getUsername());
            }
            else if(type == emailType.FORGOT_PASSWORD) {
            // Set Subject: header field
                message.setSubject("Hobbyists.com Forgot password Email");
                // Now set the actual message
                message.setText("Your OTP : "+ user.getVcode());
                
            }

            System.out.println("HERE");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            System.out.println("EXCeptioN");
            mex.printStackTrace();
        }

    }

    public static void main(String args[]) {
        UserBean user = new UserBean();
        user.setUsername("jason");
        user.setVcode(4263);
        user.setEmail("jpstarmad@gmail.com");
        sendEmail(user, emailType.REGISTER);
    }

}
