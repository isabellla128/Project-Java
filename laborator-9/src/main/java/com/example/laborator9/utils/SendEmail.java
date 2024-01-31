package com.example.laborator9.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@ApplicationScoped
public class SendEmail {
    public void sendEmail(String to, String from, String password){
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "nnbmhdzwslavtwqw");
            }

        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password for DormChoice App");
            message.setContent(
                    "<html><body>" + "<p>Hello!</p>" +
                            "<p>We hope this message finds you well. We are thrilled to inform you that you have been qualified to secure a dormitory spot through DormChoice, the ultimate platform for dormitory and room preferences at UAIC Iasi.</p>" +
                            "<p>To get started, please use the login credentials provided below:</p>"+
                            "<p><strong>Username/Email:</strong> " + to + "<br>" +
                            "<strong>Password:</strong> " + password + "</p>" +
                            "<p>Once you've logged in, you will have exclusive access to our user-friendly interface, allowing you to select your dormitory and room preferences according to your preferences and lifestyle.</p>" +
                            "<p>Thank you for choosing DormChoice! We look forward to assisting you in finding the perfect living space that suits your needs.</p>"+ "</body></html>",
                    "text/html");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
