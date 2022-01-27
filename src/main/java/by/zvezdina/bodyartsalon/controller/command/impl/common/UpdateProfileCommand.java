package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String LOGIN_IS_NOT_FREE = "update.profile.login.notFree";
    private static final String EMAIL_IS_NOT_FREE = "update.profile.email.notFree";
    private static final String EMPTY_INPUT = "";
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.FIRST_NAME, firstName);
        formData.put(RequestParameter.LAST_NAME, lastName);
        formData.put(RequestParameter.LOGIN, login);
        formData.put(RequestParameter.EMAIL, email);

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        User userToUpdate;
        try {
            userToUpdate = userService.findById(userId);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        userToUpdate.setFirstName(formData.get(RequestParameter.FIRST_NAME));
        userToUpdate.setLastName(formData.get(RequestParameter.LAST_NAME));
        userToUpdate.setLogin(formData.get(RequestParameter.LOGIN));
        userToUpdate.setEmail(formData.get(RequestParameter.EMAIL));

        boolean isDataValid = userService.validateUserData(formData);
        if (!isDataValid) {
            request.setAttribute(RequestAttribute.USER_TO_UPDATE, userToUpdate);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
        }

        String initialLogin = (String) session.getAttribute(SessionAttribute.USER_LOGIN);
        if (!initialLogin.equals(userToUpdate.getLogin())) {
            boolean loginExist;
            try {
                loginExist = userService.checkIfLoginExist(userToUpdate.getLogin());
            } catch (ServiceException e) {
                request.setAttribute(RequestAttribute.EXCEPTION, e);
                return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
            }

            if (loginExist) {
                userToUpdate.setLogin(EMPTY_INPUT);
                request.setAttribute(RequestAttribute.USER_TO_UPDATE, userToUpdate);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, LOGIN_IS_NOT_FREE);
                return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
            }
        }

        String initialEmail = (String) session.getAttribute(SessionAttribute.USER_EMAIL);
        if (!initialEmail.equals(userToUpdate.getEmail())) {
            boolean emailExist;
            try {
                emailExist = userService.checkIfEmailExist(userToUpdate.getEmail());
            } catch (ServiceException e) {
                request.setAttribute(RequestAttribute.EXCEPTION, e);
                return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
            }

            if (emailExist) {
                userToUpdate.setEmail(EMPTY_INPUT);
                request.setAttribute(RequestAttribute.USER_TO_UPDATE, userToUpdate);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, EMAIL_IS_NOT_FREE);
                return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
            }
        }

        try {
            userService.update(userToUpdate);
            session.setAttribute(SessionAttribute.USER_NAME, userToUpdate.getFirstName());
            session.setAttribute(SessionAttribute.USER_LAST_NAME, userToUpdate.getLastName());
            session.setAttribute(SessionAttribute.USER_LOGIN, userToUpdate.getLogin());
            session.setAttribute(SessionAttribute.USER_EMAIL, userToUpdate.getEmail());
            switch (userToUpdate.getRole()) {
                case ADMIN -> {return new Router(PagePath.ADMIN_PROFILE, Router.RouterType.REDIRECT);}
                case CLIENT -> {return new Router(PagePath.CLIENT_PROFILE, Router.RouterType.REDIRECT);}
                case PIERCER -> {return new Router(PagePath.PIERCER_PROFILE, Router.RouterType.REDIRECT);}
                default -> {return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.FORWARD);}
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute UpdateProfileCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
