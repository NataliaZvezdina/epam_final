package by.zvezdina.bodyartsalon.controller.command.impl.piercer;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowAppointmentsForTodayCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long piercerId = (Long) session.getAttribute(SessionAttribute.USER_ID);

        try {
            List<Appointment> appointments = appointmentService
                    .findAllByPiercerIdForCurrentDate(piercerId);
            Map<Appointment, Facility> appointmentData = new LinkedHashMap<>();
            for (Appointment appointment : appointments) {
                long facilityId = appointment.getFacilityId();
                appointmentData.put(appointment, facilityService.findById(facilityId));
            }

            request.setAttribute(RequestAttribute.APPOINTMENT_DATA, appointmentData);
            return new Router(PagePath.RELEVANT_APPOINTMENTS_BY_PIERCER, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowAppointmentsForTodayCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
