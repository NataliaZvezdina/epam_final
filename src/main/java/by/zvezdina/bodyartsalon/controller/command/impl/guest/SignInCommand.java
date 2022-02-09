package by.zvezdina.bodyartsalon.controller.command.impl.guest;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NOT_VERIFIED = "verification.error";
    private static final String NOT_ACTIVE = "signIn.notActive";
    private static final String NOT_CORRECT_INPUT = "wrong.login.or.password";
    private final UserService userService = UserServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        Optional<User> optionalUser;
        try {
            optionalUser = userService.sighIn(login, password);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!user.isVerified()) {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_VERIFIED);
                    logger.log(Level.ERROR, "Failed to execute request SignInCommand: User is not verified");
                    return new Router(PagePath.SIGN_IN, Router.RouterType.FORWARD);
                }

                if (user.getUserStatus() == UserStatus.INACTIVE) {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_ACTIVE);
                    logger.log(Level.ERROR, "Failed to execute request SignInCommand: User is not active");
                    return new Router(PagePath.SIGN_IN, Router.RouterType.FORWARD);
                }
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.AUTHORIZATION, true);
                session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
                session.setAttribute(SessionAttribute.USER_LOGIN, user.getLogin());
                session.setAttribute(SessionAttribute.USER_NAME, user.getFirstName());
                session.setAttribute(SessionAttribute.USER_LAST_NAME, user.getLastName());
                session.setAttribute(SessionAttribute.USER_ROLE, user.getRole());
                session.setAttribute(SessionAttribute.USER_EMAIL, user.getEmail());
                switch (user.getRole()) {
                    case ADMIN, PIERCER -> {return new Router(PagePath.WELCOME, Router.RouterType.REDIRECT);}
                    case CLIENT -> {
                        Client client = clientService.findById(user.getUserId());
                        int discount = clientService.findDiscountByClientId(user.getUserId());
                        session.setAttribute(SessionAttribute.USER_MONEY, client.getMoney());
                        session.setAttribute(SessionAttribute.USER_DISCOUNT, discount);
                        return new Router(PagePath.WELCOME, Router.RouterType.REDIRECT);
                    }
                    default -> {
                        logger.log(Level.ERROR, "Failed to sign in by user with unknown role");
                        request.setAttribute(RequestAttribute.EXCEPTION, "Failed to sign in by user with unknown role");
                        return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
                    }
                }
            } else {
                logger.log(Level.ERROR, "Failed to execute request LoginUserCommand: Invalid login or password");
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_CORRECT_INPUT);
                return new Router(PagePath.SIGN_IN, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute request SignInCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
