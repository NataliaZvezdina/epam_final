package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.controller.command.Router;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import by.zvezdina.bodyartsalon.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MarkOrderAsReceivedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));

        try {
            orderService.updateStatusByOrderId(orderId);
            return new Router(PagePath.ORDER_RECEIVED, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute MarkOrderAsReceivedCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
