import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // مشخصات اکانت ایمیل خود را وارد کنید
        String from = "darseik10.10@gmail.com";
        String password = "";

        // مشخصات گیرنده
        String to = "zedrix787@gmail.com";

        // اطلاعات سرور ایمیل خود را وارد کنید
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // ایجاد session با استفاده از اطلاعات اکانت ایمیل
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // ایجاد شیء MimeMessage و تنظیم مشخصات ایمیل
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Hello from JavaMail");
            message.setText("This is a test email.");

            // ارسال ایمیل
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
