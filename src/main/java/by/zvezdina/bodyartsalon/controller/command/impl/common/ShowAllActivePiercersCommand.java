package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.RequestAttribute;
import by.zvezdina.bodyartsalon.controller.command.Router;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAllActivePiercersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Piercer> piercers = piercerService.findAllActive();
            request.setAttribute(RequestAttribute.PIERCERS_LIST, piercers);
            return new Router(PagePath.PIERCERS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowAllActivePiercersCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
