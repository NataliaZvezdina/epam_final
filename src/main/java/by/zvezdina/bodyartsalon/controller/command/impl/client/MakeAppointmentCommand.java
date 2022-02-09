package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.FacilityServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeAppointmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String ALREADY_OCCUPIED = "inputData.occupied";
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private final FacilityService facilityService = FacilityServiceImpl.getInstance();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long facilityId = Long.parseLong(request.getParameter(RequestParameter.FACILITY_ID));
        String notes = request.getParameter(RequestParameter.NOTES).strip();
        String paramDate = request.getParameter(RequestParameter.DATE);
        String paramHour = request.getParameter(RequestParameter.HOUR);
        String paramPiercerId = request.getParameter(RequestParameter.PIERCER_ID);

        long piercerId;
        LocalDate date;
        int hour;

        try {
            date = LocalDate.parse(paramDate);
            hour = Integer.parseInt(paramHour);
            piercerId = Long.parseLong(paramPiercerId);
        } catch (DateTimeParseException | NumberFormatException e) {
            logger.log(Level.ERROR, "Failed to parse input data");
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.DATE, paramDate);
        formData.put(RequestParameter.NOTES, notes);
        formData.put(RequestParameter.HOUR, paramHour);

        boolean isDataValid = appointmentService.validateInputData(formData);

        try {
            Facility facility = facilityService.findById(facilityId);
            List<Piercer> piercers = piercerService.findAllActive();
            if (!isDataValid) {
                request.setAttribute(RequestAttribute.FACILITY_ID, facilityId);
                request.setAttribute(RequestAttribute.FACILITY_NAME, facility.getName());
                request.setAttribute(RequestAttribute.PIERCERS_LIST, piercers);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
                return new Router(PagePath.MAKE_APPOINTMENT, Router.RouterType.FORWARD);
            }

            LocalTime time = LocalTime.of(hour, 0);
            LocalDateTime localDateTime = LocalDateTime.of(date, time);

            boolean occupied = appointmentService.checkIfOccupied(piercerId, localDateTime);
            if (occupied) {
                request.setAttribute(RequestAttribute.FACILITY_ID, facilityId);
                request.setAttribute(RequestAttribute.FACILITY_NAME, facility.getName());
                request.setAttribute(RequestAttribute.PIERCERS_LIST, piercers);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ALREADY_OCCUPIED);
                return new Router(PagePath.MAKE_APPOINTMENT, Router.RouterType.FORWARD);
            }

            HttpSession session = request.getSession();
            Long clientId = (Long) session.getAttribute(SessionAttribute.USER_ID);

            Appointment appointment = new Appointment.Builder()
                    .facilityId(facilityId)
                    .notes(formData.get(RequestParameter.NOTES))
                    .datetime(localDateTime)
                    .clientId(clientId)
                    .piercerId(piercerId)
                    .build();

            appointmentService.create(appointment);
            return new Router(PagePath.APPOINTMENT_CREATED, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute MakeAppointmentCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
