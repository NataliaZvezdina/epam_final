package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.impl.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelAppointmentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long appointmentId = Long.parseLong(request.getParameter(RequestParameter.APPOINTMENT_ID));

        try {
            appointmentService.deleteById(appointmentId);
            return new Router(PagePath.APPOINTMENT_DELETED, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error to cancel appointment by id {} in RestoreJewelryCommand",
                    appointmentId, e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
