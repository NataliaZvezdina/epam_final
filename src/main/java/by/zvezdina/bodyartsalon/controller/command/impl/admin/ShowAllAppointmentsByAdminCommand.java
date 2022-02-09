package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowAllAppointmentsByAdminCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        try {
            List<Appointment> appointments = appointmentService.findAll(currentPage);
            Map<Appointment, Facility> appointmentData = new LinkedHashMap<>();
            for (Appointment appointment : appointments) {
                long facilityId = appointment.getFacilityId();
                appointmentData.put(appointment, facilityService.findById(facilityId));
            }

            request.setAttribute(RequestAttribute.PAGE, currentPage);
            request.setAttribute(RequestAttribute.APPOINTMENT_DATA, appointmentData);
            return new Router(PagePath.ALL_APPOINTMENTS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowAllAppointmentsByAdminCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
