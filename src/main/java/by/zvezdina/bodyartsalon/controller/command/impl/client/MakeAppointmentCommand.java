package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class MakeAppointmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long facilityId = Long.parseLong(request.getParameter(RequestParameter.FACILITY_ID));
        String notes = request.getParameter(RequestParameter.NOTES);
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

        LocalTime time = LocalTime.of(hour, 0);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);


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

        try {
            appointmentService.create(appointment);
            return new Router(PagePath.HOME, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
