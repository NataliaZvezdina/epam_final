package by.zvezdina.bodyartsalon.controller.command.impl;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
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
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final FormValidator validator = FormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);

        if (!validator.checkLogin(login) || !validator.checkPassword(password)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "login.or.password.is.not.valid");
            return new Router(currentPage, Router.RouterType.FORWARD);
        }

        Optional<User> optionalUser = null;
        try {
            optionalUser = userService.login(login, password);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!user.isVerified()) {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, "verification.error");
                    logger.log(Level.ERROR, "Failed to execute request SignInCommand: User is not verified");
                    return new Router(currentPage, Router.RouterType.FORWARD);
                }

                session.setAttribute(SessionAttribute.AUTHORIZATION, Boolean.TRUE);
                session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
                session.setAttribute(SessionAttribute.USER_LOGIN, user.getLogin());
                session.setAttribute(SessionAttribute.USER_NAME, user.getFirstName());
                session.setAttribute(SessionAttribute.USER_LAST_NAME, user.getLastName());
                session.setAttribute(SessionAttribute.USER_ROLE, user.getRole());
                session.setAttribute(SessionAttribute.USER_EMAIL, user.getEmail());
                switch (user.getRole()) {
                    case ADMIN -> {return new Router(PagePath.WELCOME, Router.RouterType.REDIRECT);}
                    case CLIENT -> {
                        Client client = clientService.findById(user.getUserId());
                        int discount = clientService.findDiscountByClientId(user.getUserId());
                        session.setAttribute(SessionAttribute.USER_MONEY, client.getMoney());
                        session.setAttribute(SessionAttribute.USER_DISCOUNT, discount);
                        return new Router(PagePath.WELCOME, Router.RouterType.REDIRECT);
                    }
                }
            } else {
                logger.log(Level.ERROR, "Failed to execute request LoginUserCommand: Invalid login or password");
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "wrong.login.or.password");
                return new Router(currentPage, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute request SignInCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
    }
}
