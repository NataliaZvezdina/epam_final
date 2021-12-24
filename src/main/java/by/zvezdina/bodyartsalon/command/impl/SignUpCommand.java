package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.util.FormValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final FormValidator validator = FormValidator.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);

        if (!validator.checkLogin(login)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect input login");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!validator.checkPassword(password)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect input password");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!repeatPassword.equals(password)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Wrong password repeated");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!validator.checkFirstName(firstName)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect input first name");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!validator.checkLastName(lastName)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect input last name");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!validator.checkEmail(email)) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect input email");
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        Client client = new Client.Builder()
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        try {
            clientService.create(client);
        } catch (ServiceException e) {
            new Router(PagePath.ERROR_404_PAGE, Router.RouterType.FORWARD);
        }

        return new Router(PagePath.HOME, Router.RouterType.REDIRECT);
    }
}
