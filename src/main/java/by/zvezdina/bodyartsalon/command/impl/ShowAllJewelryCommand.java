package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.Command;
import by.zvezdina.bodyartsalon.command.PagePath;
import by.zvezdina.bodyartsalon.command.RequestAttribute;
import by.zvezdina.bodyartsalon.command.Router;
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
            List<Jewelry> alljewelry = jewelryService.findAll();
            request.setAttribute(RequestAttribute.JEWELRY_LIST, alljewelry);
        } catch (ServiceException e) {
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.JEWELRY, Router.RouterType.FORWARD);
    }
}
