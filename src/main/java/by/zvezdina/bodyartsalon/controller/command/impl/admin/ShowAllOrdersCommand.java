package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import by.zvezdina.bodyartsalon.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ShowAllOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        List<Order> allOrders;
        try {
            allOrders = orderService.findAll(currentPage);
            request.setAttribute(RequestAttribute.ALL_ORDERS, allOrders);
            request.setAttribute(RequestAttribute.PAGE, currentPage);
            return new Router(PagePath.ORDERS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding all orders by admin: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
