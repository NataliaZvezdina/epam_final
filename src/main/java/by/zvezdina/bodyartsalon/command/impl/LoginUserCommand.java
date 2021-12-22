package by.zvezdina.bodyartsalon.command.impl;


import by.zvezdina.bodyartsalon.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginUserCommand implements Command { // to delete
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        logger.log(Level.INFO, "reqг attr login: {}, password:{}", login, password);

        Optional<User> optionalUser = null;
        try {
            optionalUser = userService.login(login, password);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute request LoginUserCommand: ", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }

        if (optionalUser.isEmpty()) {
            logger.log(Level.ERROR, "Failed to execute request LoginUserCommand: Invalid login or password");
            request.getSession().setAttribute(RequestAttribute.MESSAGE, "Invalid login or password");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
        }

        request.getSession().setAttribute(RequestAttribute.USER, optionalUser.get());
        return new Router(PagePath.GO_TO_PROFILE, Router.RouterType.REDIRECT);
    }
}
