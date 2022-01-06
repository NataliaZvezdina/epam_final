package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class EditJewelryCommand implements Command {
    JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        String imageUrl = request.getParameter(RequestParameter.IMAGE_URL);
        String type = request.getParameter(RequestParameter.TYPE);
        String manufacturer = request.getParameter(RequestParameter.MANUFACTURER);
        double priceDouble = Double.parseDouble(request.getParameter(RequestParameter.PRICE));
        BigDecimal price = BigDecimal.valueOf(priceDouble);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        boolean isAvailable = Boolean.parseBoolean(request.getParameter(RequestParameter.IS_AVAILABLE));
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));
        Router router;

        // todo validation

        Jewelry jewelryToUpdate = new Jewelry.Builder()
                .jewelryId(jewelryId)
                .imageUrl(imageUrl)
                .type(type)
                .manufacturer(manufacturer)
                .description(description)
                .price(price)
                .isAvailable(isAvailable)
                .build();

        try {
            jewelryService.update(jewelryToUpdate);
            router = new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + page, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}
