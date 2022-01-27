package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY_INPUT = "";
    private static final String INVALID_INPUT_DATA = "inputData.invalid";
    private static final String NOT_MATCHING_PASSWORDS = "input.notMatching";
    private static final String WRONG_PASSWORD = "wrong.input.password";
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String oldPassword = request.getParameter(RequestParameter.OLD_PASSWORD);
        String newPassword = request.getParameter(RequestParameter.NEW_PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.OLD_PASSWORD, oldPassword);
        formData.put(RequestParameter.NEW_PASSWORD, newPassword);

        boolean isDataValid = userService.validateInputPasswords(formData);

        String oldPasswordValidated = formData.get(RequestParameter.OLD_PASSWORD);
        String newPasswordValidated = formData.get(RequestParameter.NEW_PASSWORD);

        if (!isDataValid) {
            request.setAttribute(RequestAttribute.OLD_PASSWORD, oldPasswordValidated);
            request.setAttribute(RequestAttribute.NEW_PASSWORD, newPasswordValidated);
            request.setAttribute(RequestAttribute.REPEAT_PASSWORD, EMPTY_INPUT);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT_DATA);
            return new Router(PagePath.UPDATE_PASSWORD, Router.RouterType.FORWARD);
        }

        if (!repeatPassword.equals(newPasswordValidated)) {
            request.setAttribute(RequestAttribute.OLD_PASSWORD, oldPasswordValidated);
            request.setAttribute(RequestAttribute.NEW_PASSWORD, newPasswordValidated);
            request.setAttribute(RequestAttribute.REPEAT_PASSWORD, EMPTY_INPUT);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, NOT_MATCHING_PASSWORDS);
            return new Router(PagePath.UPDATE_PASSWORD, Router.RouterType.FORWARD);
        }

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);

        try {
            boolean isOldPasswordReal = userService.checkUserPassword(userId, oldPasswordValidated);
            if (!isOldPasswordReal) {
                request.setAttribute(RequestAttribute.OLD_PASSWORD, oldPasswordValidated);
                request.setAttribute(RequestAttribute.NEW_PASSWORD, newPasswordValidated);
                request.setAttribute(RequestAttribute.REPEAT_PASSWORD, repeatPassword);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, WRONG_PASSWORD);
                return new Router(PagePath.UPDATE_PASSWORD, Router.RouterType.FORWARD);
            }
            userService.updatePassword(userId, newPasswordValidated);
            session.invalidate();
            return new Router(PagePath.SIGN_IN, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute UpdatePasswordCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
