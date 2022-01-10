package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class EditFacilityCommand implements Command {
    private static final String INVALID_INPUT = "inputData.invalid";
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long facilityId = Long.parseLong(request.getParameter(RequestParameter.FACILITY_ID));

        String name = request.getParameter(RequestParameter.NAME);
        String facilityDescription = request.getParameter(RequestParameter.FACILITY_DESCRIPTION);
        String priceString = request.getParameter(RequestParameter.FACILITY_PRICE);

        boolean isAccessible = Boolean.parseBoolean(request.getParameter(RequestParameter.IS_ACCESSIBLE));
        int page = Integer.parseInt(request.getParameter(RequestParameter.PAGE));

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.NAME, name);
        formData.put(RequestParameter.FACILITY_DESCRIPTION, facilityDescription);
        formData.put(RequestParameter.FACILITY_PRICE, priceString);

        boolean isFormValid = facilityService.validateInputData(formData);

        BigDecimal price = new BigDecimal(formData.get(RequestParameter.FACILITY_PRICE));

        Facility facilityToUpdate = new Facility.Builder()
                .facilityId(facilityId)
                .name(formData.get(RequestParameter.NAME))
                .description(formData.get(RequestParameter.FACILITY_DESCRIPTION))
                .price(price)
                .accessible(isAccessible)
                .build();

        if (!isFormValid) {
            request.setAttribute(RequestAttribute.FACILITY_TO_EDIT, facilityToUpdate);
            request.setAttribute(RequestAttribute.PAGE, page);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.EDIT_FACILITY, Router.RouterType.FORWARD);
        }

        try {
            facilityService.update(facilityToUpdate);
            return new Router(PagePath.GO_TO_FACILITY_DEFINED_PAGE + page, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
