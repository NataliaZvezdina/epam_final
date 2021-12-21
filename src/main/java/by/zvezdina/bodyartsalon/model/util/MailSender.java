package by.zvezdina.bodyartsalon.model.util;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_PROPERTIES_PATH = "src/main/resources/mail.properties";
    private static final String MAIL_SUBJECT = "Verification";
    private final Properties properties = new Properties();
    private MimeMessage message;

    public void send(String userMail, String messageText) {
        try {
            properties.load(new FileReader(MAIL_PROPERTIES_PATH));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Failed to load properties from file {}: {}", MAIL_PROPERTIES_PATH, e );
        }

        try {
            initMessage(userMail, messageText);
            Transport.send(message);
        } catch (AddressException e) {
            logger.warn("Invalid address: {} {}", userMail, e);
        } catch (MessagingException e) {
            logger.warn("Error generating or sending message: ", e);
        }
    }

    private void initMessage(String userMail, String messageText) throws MessagingException {
        Session mailSession = createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(MAIL_SUBJECT);
        message.setContent(messageText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
    }

    private Session createSession(Properties properties) {
        String userName = properties.getProperty("mail.user.name");
        String userPassword = properties.getProperty("mail.user.password");
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
