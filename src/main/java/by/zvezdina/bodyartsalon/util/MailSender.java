package by.zvezdina.bodyartsalon.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Mail sender.
 */
public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_PROPERTIES_PATH = "mail.properties";
    private static final String MAIL_SUBJECT = "Verification";
    private static final String CONTENT_TYPE = "text/html";
    private static final String PROPERTY_USER_NAME = "mail.user.name";
    private static final String PROPERTY_USER_PASSWORD = "mail.user.password";
    private final Properties properties = new Properties();
    private MimeMessage message;

    /**
     * Send.
     *
     * @param userMail    the user mail
     * @param messageText the message text
     */
    public void send(String userMail, String messageText) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(MAIL_PROPERTIES_PATH);
            properties.load(inputStream);
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
        message.setContent(messageText, CONTENT_TYPE);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
    }

    private Session createSession(Properties properties) {
        String userName = properties.getProperty(PROPERTY_USER_NAME);
        String userPassword = properties.getProperty(PROPERTY_USER_PASSWORD);
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
