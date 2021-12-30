package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowAllJewelryCommand implements Command {

    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            String page = request.getParameter(RequestParameter.PAGE);
            int currentPageNumber = 0;
            if (page != null) {
                currentPageNumber = Integer.parseInt(page);
            } else {
                currentPageNumber = 1;
            }
            List<Jewelry> allJewelry = jewelryService.findAll(currentPageNumber);
            request.setAttribute(RequestAttribute.JEWELRY_LIST, allJewelry);
            request.setAttribute(RequestAttribute.PAGE, currentPageNumber);

        } catch (ServiceException e) {
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.JEWELRY, Router.RouterType.FORWARD);
    }
}
