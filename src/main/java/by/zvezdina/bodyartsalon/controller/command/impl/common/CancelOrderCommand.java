package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.OrderItemService;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderItemServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CancelOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final OrderItemService orderItemService = OrderItemServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));
        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute(SessionAttribute.USER_ROLE);

        try {
            orderService.deleteById(orderId);
            Order order = orderService.findById(orderId);
            BigDecimal orderCost = orderItemService.calculateItemsCostByOrderId(orderId);
            clientService.updateBalance(order.getClientId(), orderCost);
            if (userRole == Role.CLIENT) {
                Long clientId = (Long) session.getAttribute(SessionAttribute.USER_ID);
                Client client = clientService.findById(clientId);
                session.setAttribute(SessionAttribute.USER_MONEY, client.getMoney());
            }
            return new Router(PagePath.ORDER_CANCELLED, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute CancelOrderCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
