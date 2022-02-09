package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAllJewelryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPageNumber = page != null ? Integer.parseInt(page) : 1;

        try {
            List<Jewelry> allJewelry = jewelryService.findAll(currentPageNumber);
            request.setAttribute(RequestAttribute.JEWELRY_LIST, allJewelry);
            request.setAttribute(RequestAttribute.PAGE, currentPageNumber);
            return new Router(PagePath.JEWELRY, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowAllJewelryCommand:", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
