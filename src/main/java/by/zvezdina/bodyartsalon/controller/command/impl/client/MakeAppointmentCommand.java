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
        long piercerId = 0;
        LocalDate date = null;
        int hour = 0;

        // todo needs validation
        try {
            date = LocalDate.parse(request.getParameter(RequestParameter.DATE));
            hour = Integer.parseInt(request.getParameter(RequestParameter.HOUR));
            piercerId = Long.parseLong(request.getParameter(RequestParameter.PIERCER_ID));
        } catch (DateTimeParseException | NumberFormatException e) {
            logger.log(Level.ERROR, "Failed to parse input data");
            return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
        }

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.DATE, date.toString());
        formData.put(RequestParameter.NOTES, notes);

        boolean isDataValid = appointmentService.validateInputData(formData);

        try {
            Facility facility = facilityService.findById(facilityId);
            List<Piercer> piercers = piercerService.findAllActive();
            if (!isDataValid) {
                request.setAttribute(RequestAttribute.FACILITY_ID, facilityId);
                request.setAttribute(RequestAttribute.FACILITY_NAME, facility.getName());
                request.setAttribute(RequestAttribute.PIERCER, piercerId);
                request.setAttribute(RequestAttribute.PIERCERS_LIST, piercers);
                request.setAttribute(RequestAttribute.FORM_DATA, formData);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
                return new Router(PagePath.MAKE_APPOINTMENT, Router.RouterType.FORWARD);
            }

            String REGEXP = "(1[0-9])|20";
            String parameter = request.getParameter(RequestParameter.HOUR);
            System.out.println(parameter);
            System.out.println("Matches -> " + request.getParameter(RequestParameter.HOUR).matches(REGEXP));
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

            System.out.println("date --> " + date);
            System.out.println("time --> " + time);
            System.out.println("LocalDateTime --> " + localDateTime);

            HttpSession session = request.getSession();
            Long clientId = (Long) session.getAttribute(SessionAttribute.USER_ID);

            Appointment appointment = new Appointment.Builder()
                    .facilityId(facilityId)
                    .notes(notes)
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
