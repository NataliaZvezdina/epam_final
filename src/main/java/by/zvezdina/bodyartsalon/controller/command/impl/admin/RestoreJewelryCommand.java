package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestoreJewelryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int jewelryId = Integer.parseInt(request.getParameter(RequestParameter.JEWELRY_ID));
        try {
            jewelryService.restoreById(jewelryId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error to restore jewelry by id {} in RestoreJewelryCommand",
                    jewelryId, e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        String page = request.getParameter(RequestParameter.PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;

        return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + currentPage, Router.RouterType.FORWARD);
    }
}