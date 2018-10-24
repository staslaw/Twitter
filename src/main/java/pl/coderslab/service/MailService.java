package pl.coderslab.service;

import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public String generateAndSendEmail(String email, String password) {
        String message = "";
        try {
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            generateMailMessage.setSubject("Your account has been activated");
            String emailBody = "enter your email as your login" + "<br>password: " + password + "<br><br> Regards, <br>Twitter";
            generateMailMessage.setContent(emailBody, "text/html");

            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "twitterprojectnoreply@gmail.com", "coderslab");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            message = "Mail aktywacyjny został wysłany.";
        } catch (Exception e) {
            message = "Mail aktywacyjny nie został wysłany.";
        }
        return message;
    }


        public void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail("sniemyski@o2.pl", "password");
    }
}