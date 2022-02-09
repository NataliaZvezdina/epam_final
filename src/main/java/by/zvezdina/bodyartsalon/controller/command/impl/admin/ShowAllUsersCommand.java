package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.RequestAttribute;
import by.zvezdina.bodyartsalon.controller.command.Router;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        List<User> allUsers;
        try {
            allUsers = userService.findAll();
            request.setAttribute(RequestAttribute.USERS_LIST, allUsers);
            return new Router(PagePath.ALL_USERS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowAllUsersCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
