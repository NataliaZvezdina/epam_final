package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AddFacilityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String FIRST_PAGE = "1";
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        String name = request.getParameter(RequestParameter.NAME).strip();
        String priceString = request.getParameter(RequestParameter.FACILITY_PRICE);
        String description = request.getParameter(RequestParameter.FACILITY_DESCRIPTION).strip();

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.NAME, name);
        formData.put(RequestParameter.FACILITY_PRICE, priceString);
        formData.put(RequestParameter.FACILITY_DESCRIPTION, description);

        boolean isFormValid = facilityService.validateInputData(formData);

        if (!isFormValid) {
            request.setAttribute(RequestAttribute.FACILITY_TO_ADD, formData);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.ADD_FACILITY, Router.RouterType.FORWARD);
        }

        BigDecimal price = new BigDecimal(formData.get(RequestParameter.FACILITY_PRICE));

        Facility facilityToCreate = new Facility.Builder()
                .name(formData.get(RequestParameter.NAME))
                .description(formData.get(RequestParameter.FACILITY_DESCRIPTION))
                .price(price)
                .accessible(true)
                .build();

        try {
            facilityService.create(facilityToCreate);
            return new Router(PagePath.GO_TO_FACILITY_DEFINED_PAGE + FIRST_PAGE,
                    Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute AddFacilityCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
