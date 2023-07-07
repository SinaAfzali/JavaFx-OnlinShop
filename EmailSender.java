package com.example.OnlineShop;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static int send(String email,String Subject,String Content) {
        // Sender's email configuration
        String senderEmail = "NonShopMe@gmail.com";
        String senderPassword = "qkcfyborxcsoduwi";


        // Recipient's email address
        String recipientEmail = email ;

        // Sender's SMTP server configuration
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        // Create properties for the SMTP connection
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set email subject and content
            message.setSubject(Subject);
            message.setText(Content);

            // Send the message
            Transport.send(message);

            return 0;
        } catch (MessagingException e) {
            return 1;
        }
    }
}
