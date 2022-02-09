package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToEditJewelryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPageNumber = page != null ? Integer.parseInt(page) : 1;

        try {
            Jewelry foundJewelry = jewelryService.findById(jewelryId);
            request.setAttribute(RequestAttribute.JEWELRY_TO_EDIT, foundJewelry);
            request.setAttribute(RequestAttribute.PAGE, currentPageNumber);
            return new Router(PagePath.EDIT_JEWELRY, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to find execute GoToEditJewelryCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
