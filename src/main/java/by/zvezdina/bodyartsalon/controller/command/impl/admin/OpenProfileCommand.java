package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.*;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OpenProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = null;
        long userId = Long.parseLong(request.getParameter(RequestParameter.USER_ID));
        Role role = Role.valueOf(request.getParameter(RequestParameter.USER_ROLE));

        User admin;
        Client client;
        Piercer piercer;
        try {
            switch (role) {
                case ADMIN -> {
                    admin = userService.findById(userId);
                    request.setAttribute(RequestAttribute.ADMIN, admin);
                    router = new Router(PagePath.OPEN_USER_PROFILE, Router.RouterType.FORWARD);
                }
                case CLIENT -> {
                    client = clientService.findById(userId);
                    int discount = clientService.findDiscountByClientId(userId);
                    List<Discount> discounts = clientService.findAllDiscounts();
                    request.setAttribute(RequestAttribute.CLIENT, client);
                    request.setAttribute(RequestAttribute.DISCOUNT, discount);
                    request.setAttribute(RequestAttribute.DISCOUNT_LIST, discounts);
                    router = new Router(PagePath.OPEN_USER_PROFILE, Router.RouterType.FORWARD);
                }
                case PIERCER -> {
                    piercer = piercerService.findById(userId);
                    request.setAttribute(RequestAttribute.PIERCER, piercer);
                    router = new Router(PagePath.OPEN_USER_PROFILE, Router.RouterType.FORWARD);
                }
                default -> {
                    logger.log(Level.ERROR, "Failed to open user profile with unknown role");
                    return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute OpenProfileCommand: ", e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}
