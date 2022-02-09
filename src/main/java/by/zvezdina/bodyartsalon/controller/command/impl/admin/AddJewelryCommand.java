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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AddJewelryCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String FIRST_PAGE = "1";
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String imageUrl = request.getParameter(RequestParameter.IMAGE_URL);
        String type = request.getParameter(RequestParameter.TYPE).strip();
        String manufacturer = request.getParameter(RequestParameter.MANUFACTURER).strip();
        String priceString = request.getParameter(RequestParameter.PRICE);
        String description = request.getParameter(RequestParameter.DESCRIPTION).strip();

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.IMAGE_URL, imageUrl);
        formData.put(RequestParameter.TYPE, type);
        formData.put(RequestParameter.MANUFACTURER, manufacturer);
        formData.put(RequestParameter.PRICE, priceString);
        formData.put(RequestParameter.DESCRIPTION, description);

        boolean isFormValid = jewelryService.validateInputData(formData);

        if (!isFormValid) {
            request.setAttribute(RequestAttribute.JEWELRY_TO_ADD, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.ADD_JEWELRY, Router.RouterType.FORWARD);
        }

        BigDecimal price = new BigDecimal(formData.get(RequestParameter.PRICE));

        Jewelry jewelryToCreate = new Jewelry.Builder()
                .imageUrl(formData.get(RequestParameter.IMAGE_URL))
                .type(formData.get(RequestParameter.TYPE))
                .manufacturer(formData.get(RequestParameter.MANUFACTURER))
                .description(formData.get(RequestParameter.DESCRIPTION))
                .price(price)
                .isAvailable(true)
                .build();

        try {
            jewelryService.create(jewelryToCreate);
            return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + FIRST_PAGE,
                    Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute AddJewelryCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
