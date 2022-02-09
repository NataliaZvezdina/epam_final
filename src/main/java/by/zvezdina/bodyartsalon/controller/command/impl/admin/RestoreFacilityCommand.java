package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.controller.command.Router;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestoreFacilityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int facilityId = Integer.parseInt(request.getParameter(RequestParameter.FACILITY_ID));
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        try {
            facilityService.restoreById(facilityId);
            return new Router(PagePath.GO_TO_FACILITY_DEFINED_PAGE + currentPage, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute RestoreFacilityCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
