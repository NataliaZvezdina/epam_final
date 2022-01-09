package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class EditJewelryCommand implements Command {
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        String imageUrl = request.getParameter(RequestParameter.IMAGE_URL);
        String type = request.getParameter(RequestParameter.TYPE);
        String manufacturer = request.getParameter(RequestParameter.MANUFACTURER);
        String priceString = request.getParameter(RequestParameter.PRICE);
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        boolean isAvailable = Boolean.parseBoolean(request.getParameter(RequestParameter.IS_AVAILABLE));
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.IMAGE_URL, imageUrl);
        formData.put(RequestParameter.TYPE, type);
        formData.put(RequestParameter.MANUFACTURER, manufacturer);
        formData.put(RequestParameter.PRICE, priceString);
        formData.put(RequestParameter.DESCRIPTION, description);

        boolean isFormValid = jewelryService.validateInputData(formData);

        double priceDouble = Double.parseDouble(formData.get(RequestParameter.PRICE));
        BigDecimal price = BigDecimal.valueOf(priceDouble);

        Jewelry jewelryToUpdate = new Jewelry.Builder()
                .jewelryId(jewelryId)
                .imageUrl(formData.get(RequestParameter.IMAGE_URL))
                .type(formData.get(RequestParameter.TYPE))
                .manufacturer(formData.get(RequestParameter.MANUFACTURER))
                .description(formData.get(RequestParameter.DESCRIPTION))
                .price(price)
                .isAvailable(isAvailable)
                .build();

        if (!isFormValid) {
            request.setAttribute(RequestAttribute.JEWELRY_TO_EDIT, jewelryToUpdate);
            request.setAttribute(RequestAttribute.PAGE, page);
            return new Router(PagePath.EDIT_JEWELRY, Router.RouterType.FORWARD);  // todo add error message
        }

        try {
            jewelryService.update(jewelryToUpdate);
            return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + page, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
