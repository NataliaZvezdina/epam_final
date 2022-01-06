package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class GoToEditJewelryCommand implements Command {
    JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPageNumber = page != null ? Integer.parseInt(page) : 1;
        Router router;

        try {
            Jewelry foundJewelry = jewelryService.findById(jewelryId);
            request.setAttribute(RequestAttribute.JEWELRY_TO_EDIT, foundJewelry);
            request.setAttribute(RequestAttribute.PAGE, currentPageNumber);
            router = new Router(PagePath.EDIT_JEWELRY, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        return router;
    }
}
