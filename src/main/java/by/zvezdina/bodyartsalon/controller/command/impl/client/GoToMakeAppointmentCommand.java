package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToMakeAppointmentCommand implements Command {
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        long facilityId = Long.parseLong(request.getParameter(RequestParameter.FACILITY_ID));

        try {
            Facility facility = facilityService.findById(facilityId);
            List<Piercer> piercers = piercerService.findAllActive();

            request.setAttribute(RequestAttribute.FACILITY_ID, facilityId);
            request.setAttribute(RequestAttribute.FACILITY_NAME, facility.getName());
            request.setAttribute(RequestAttribute.PIERCERS_LIST, piercers);
            return new Router(PagePath.MAKE_APPOINTMENT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
