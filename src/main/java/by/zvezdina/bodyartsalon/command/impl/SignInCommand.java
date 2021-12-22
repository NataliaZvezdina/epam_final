package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import by.zvezdina.bodyartsalon.model.util.FormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();
    private final FormValidator validator = FormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);

        if (!validator.checkLogin(login) || !validator.checkPassword(password)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Invalid login or password");
            return new Router(currentPage, Router.RouterType.FORWARD);
        }

        Optional<User> optionalUser = null;
        try {
            optionalUser = userService.login(login, password);
            if (optionalUser.isEmpty()) {
                logger.log(Level.ERROR, "Failed to execute request LoginUserCommand: Invalid login or password");
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Invalid login or password");
                return new Router(currentPage, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute request SignInCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        session.setAttribute(SessionAttribute.AUTHORIZATION, Boolean.TRUE);
        User user = optionalUser.get();
        Role role = user.getRole();
        switch (role) {
            case ADMIN: {
                session.setAttribute(SessionAttribute.USER_PHONE, user.getPhone());
                session.setAttribute(SessionAttribute.USER_NAME, user.getFirstName());
                return new Router(PagePath.PROFILE, Router.RouterType.REDIRECT);  // todo
            }
            case CLIENT: {
                session.setAttribute(SessionAttribute.USER_PHONE, user.getPhone());
                return new Router(PagePath.PROFILE, Router.RouterType.REDIRECT);  // todo
            }
        }

        return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
    }
}
