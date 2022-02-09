package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToEditPiercerWorkingInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long piercerId = Long.parseLong(request.getParameter(RequestParameter.PIERCER_ID));

        try {
            Piercer piercer = piercerService.findById(piercerId);
            request.setAttribute(RequestAttribute.PIERCER_TO_EDIT, piercer);
            return new Router(PagePath.EDIT_PIERCER_WORKING_INFO, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute GoToEditPiercerWorkingInfoCommand: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
