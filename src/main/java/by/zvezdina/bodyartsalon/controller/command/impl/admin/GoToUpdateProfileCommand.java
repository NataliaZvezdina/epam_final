package by.zvezdina.bodyartsalon.controller.command.impl.admin;

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

public class GoToUpdateProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);

        try {
            User userToUpdate = userService.findById(userId);
            request.setAttribute(RequestAttribute.USER_TO_UPDATE, userToUpdate);
            return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute GoToUpdateProfileCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
