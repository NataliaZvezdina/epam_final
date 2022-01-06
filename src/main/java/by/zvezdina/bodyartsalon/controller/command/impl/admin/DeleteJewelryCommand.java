package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.controller.command.Router;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteJewelryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int jewelryId = Integer.parseInt(request.getParameter(RequestParameter.JEWELRY_ID));
        boolean deleted = false;
        try {
            deleted = jewelryService.deleteById(jewelryId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error to delete jewelry by id {} in DeleteJewelryCommand", jewelryId, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        String page = (String) request.getAttribute(RequestParameter.PAGE);
        int currentPage = 0;
        if (page != null) {
            currentPage = Integer.parseInt(page);
        } else {
            currentPage = 1;
        }
        // todo debug returned page
        return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + currentPage, Router.RouterType.FORWARD);
    }
}
