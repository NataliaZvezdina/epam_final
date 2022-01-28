package by.zvezdina.bodyartsalon.controller.tag;

import by.zvezdina.bodyartsalon.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class WelcomeTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute(SessionAttribute.LOCALE));
        ResourceBundle bundle = null;
        InputStream stream = WelcomeTag.class.getClassLoader()
                .getResourceAsStream("bundle/locale_" + language + ".properties");
        if (stream != null) {
            try (stream) {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String registration = bundle.getString("welcome.registration");
        String role = bundle.getString("welcome.role");

        JspWriter out = pageContext.getOut();

        try {
            out.write("<p>" + registration + " " + session.getAttribute(SessionAttribute.USER_NAME) +
                     " " + session.getAttribute(SessionAttribute.USER_LAST_NAME) + "</p>");
            out.write("<p>" + role + " " + session.getAttribute(SessionAttribute.USER_ROLE) + "</p>");
        } catch (IOException e) {
            logger.log(Level.ERROR, "Failed while writing to stream");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
