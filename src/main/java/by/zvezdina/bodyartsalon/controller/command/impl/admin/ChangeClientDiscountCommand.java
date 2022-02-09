package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeClientDiscountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        long discountId = Long.parseLong(request.getParameter(RequestParameter.NEW_DISCOUNT_ID));
        long clientId = Long.parseLong(request.getParameter(RequestParameter.CLIENT_ID));

        try {
            clientService.updateClientDiscount(clientId, discountId);
            router = new Router(PagePath.RETURN_TO_CLIENT_PROFILE + clientId, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ChangeClientDiscountCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        return router;
    }
}
