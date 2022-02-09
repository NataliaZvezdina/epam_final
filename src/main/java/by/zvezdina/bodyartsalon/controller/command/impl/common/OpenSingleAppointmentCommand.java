package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenSingleAppointmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long appointmentId = Long.parseLong(request.getParameter(RequestParameter.APPOINTMENT_ID));

        try {
            Appointment appointment = appointmentService.findById(appointmentId);
            Client client = clientService.findById(appointment.getClientId());
            Piercer piercer = piercerService.findById(appointment.getPiercerId());
            Facility facility = facilityService.findById(appointment.getFacilityId());

            String firstName = client.getFirstName();
            String lastName = client.getLastName();

            String piercerFullName = piercer.getFirstName() + " " + piercer.getLastName();

            request.setAttribute(RequestAttribute.PIERCER_FULL_NAME, piercerFullName);
            request.setAttribute(RequestAttribute.FIRST_NAME, firstName);
            request.setAttribute(RequestAttribute.LAST_NAME, lastName);
            request.setAttribute(RequestAttribute.APPOINTMENT, appointment);
            request.setAttribute(RequestAttribute.FACILITY, facility);
            return new Router(PagePath.SINGLE_APPOINTMENT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute OpenSingleAppointmentCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
