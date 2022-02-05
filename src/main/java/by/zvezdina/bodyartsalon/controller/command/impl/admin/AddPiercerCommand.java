package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AddPiercerCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String NOT_MATCHING_PASSWORDS = "input.notMatching";
    private static final String LOGIN_IS_NOT_FREE = "update.profile.login.notFree";
    private static final String EMAIL_IS_NOT_FREE = "update.profile.email.notFree";
    private static final String EMPTY_STRING = "";
    private final UserService userService = UserServiceImpl.getInstance();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String imageUrl = request.getParameter(RequestParameter.IMAGE_URL);
        String infoAbout = request.getParameter(RequestParameter.INFO_ABOUT).strip();

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.FIRST_NAME, firstName);
        formData.put(RequestParameter.LAST_NAME, lastName);
        formData.put(RequestParameter.LOGIN, login);
        formData.put(RequestParameter.EMAIL, email);
        formData.put(RequestParameter.PASSWORD, password);
        formData.put(RequestParameter.REPEAT_PASSWORD, repeatPassword);
        formData.put(RequestParameter.IMAGE_URL, imageUrl);
        formData.put(RequestParameter.INFO_ABOUT, infoAbout);

        boolean isDataValid = userService.validateUserData(formData);
        if (!isDataValid) {
            request.setAttribute(RequestAttribute.FORM_DATA, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.ADD_PIERCER, Router.RouterType.FORWARD);
        }

        if (!formData.get(RequestParameter.PASSWORD).equals(formData.get(RequestParameter.REPEAT_PASSWORD))) {
            formData.put(RequestParameter.REPEAT_PASSWORD, EMPTY_STRING);
            request.setAttribute(RequestAttribute.FORM_DATA, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_MATCHING_PASSWORDS);
            return new Router(PagePath.ADD_PIERCER, Router.RouterType.FORWARD);
        }

        try {
            boolean loginExist = userService.checkIfLoginExist(formData.get(RequestParameter.LOGIN));
            if (loginExist) {
                formData.put(RequestParameter.LOGIN, EMPTY_STRING);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, LOGIN_IS_NOT_FREE);
                return new Router(PagePath.ADD_PIERCER, Router.RouterType.FORWARD);
            }
            boolean emailExist = userService.checkIfEmailExist(formData.get(RequestParameter.EMAIL));
            if (emailExist) {
                formData.put(RequestParameter.EMAIL, EMPTY_STRING);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, EMAIL_IS_NOT_FREE);
                return new Router(PagePath.ADD_PIERCER, Router.RouterType.FORWARD);
            }

            Piercer piercerToCreate = new Piercer.Builder()
                    .login(login)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .role(Role.PIERCER)
                    .userStatus(UserStatus.ACTIVE)
                    .isVerified(true)
                    .photoUrl(formData.get(RequestParameter.IMAGE_URL))
                    .infoAbout(formData.get(RequestParameter.INFO_ABOUT))
                    .build();

            piercerService.create(piercerToCreate);
            return new Router(PagePath.GO_TO_UPDATED_USERS_LIST, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute AddPiercerCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
