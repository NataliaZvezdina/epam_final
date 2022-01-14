package by.zvezdina.bodyartsalon.controller.command.impl;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String NOT_MATCHING_PASSWORDS = "input.notMatching";
    private static final String LOGIN_IS_NOT_FREE = "update.profile.login.notFree";
    private static final String EMAIL_IS_NOT_FREE = "update.profile.email.notFree";
    private static final String EMPTY_STRING = "";
    private final UserService userService = UserServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.FIRST_NAME, firstName);
        formData.put(RequestParameter.LAST_NAME, lastName);
        formData.put(RequestParameter.LOGIN, login);
        formData.put(RequestParameter.EMAIL, email);
        formData.put(RequestParameter.PASSWORD, password);
        formData.put(RequestParameter.REPEAT_PASSWORD, repeatPassword);

        boolean isDataValid = userService.validateUserData(formData);
        if (!isDataValid) {
            request.setAttribute(RequestAttribute.FORM_DATA, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        if (!formData.get(RequestParameter.PASSWORD).equals(formData.get(RequestParameter.REPEAT_PASSWORD))) {
            formData.put(RequestParameter.REPEAT_PASSWORD, EMPTY_STRING);
            request.setAttribute(RequestAttribute.FORM_DATA, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_MATCHING_PASSWORDS);
            return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
        }

        try {
            boolean loginExist = userService.checkIfLoginExist(formData.get(RequestParameter.LOGIN));
            if (loginExist) {
                formData.put(RequestParameter.LOGIN, EMPTY_STRING);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, LOGIN_IS_NOT_FREE);
                return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
            }
            boolean emailExist = userService.checkIfEmailExist(formData.get(RequestParameter.EMAIL));
            if (emailExist) {
                formData.put(RequestParameter.EMAIL, EMPTY_STRING);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, EMAIL_IS_NOT_FREE);
                return new Router(PagePath.SIGN_UP, Router.RouterType.FORWARD);
            }

            Client client = new Client.Builder()
                    .login(login)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .role(Role.CLIENT)
                    .userStatus(UserStatus.INACTIVE)
                    .isVerified(false)
                    .build();
            Client created = clientService.create(client);
            return new Router(PagePath.NEW_CLIENT_SIGNED_UP, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
