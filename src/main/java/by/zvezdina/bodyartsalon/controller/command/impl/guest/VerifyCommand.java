package by.zvezdina.bodyartsalon.controller.command.impl.guest;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerifyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int clientId = Integer.parseInt(request.getParameter(RequestParameter.ID));
        boolean verified;
        try {
            verified = clientService.verify(clientId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error to verify client in VerifyCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
        request.setAttribute(RequestAttribute.VERIFICATION, verified);
        return new Router(PagePath.VERIFICATION, Router.RouterType.FORWARD);
    }
}
