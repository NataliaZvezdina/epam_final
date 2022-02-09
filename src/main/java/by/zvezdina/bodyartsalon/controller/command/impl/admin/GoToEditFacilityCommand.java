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

public class GoToEditFacilityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long facilityId = Long.parseLong(request.getParameter(RequestParameter.FACILITY_ID));
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPageNumber = page != null ? Integer.parseInt(page) : 1;

        try {
            Facility foundFacility = facilityService.findById(facilityId);
            request.setAttribute(RequestAttribute.FACILITY_TO_EDIT, foundFacility);
            request.setAttribute(RequestAttribute.PAGE, currentPageNumber);
            return new Router(PagePath.EDIT_FACILITY, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to find execute GoToEditFacilityCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
