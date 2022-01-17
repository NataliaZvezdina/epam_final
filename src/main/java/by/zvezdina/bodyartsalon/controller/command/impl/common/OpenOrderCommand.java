package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.OrderItemService;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderItemServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final OrderItemService orderItemService = OrderItemServiceImpl.getInstance();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ORDER_ID));

        try {
            Order order = orderService.findById(orderId);
            Client client = clientService.findById(order.getClientId());
            int discount = clientService.findDiscountByClientId(client.getUserId());
            List<OrderItem> orderItems = orderItemService.findAllByOrderId(orderId);

            Map<Jewelry, Integer> items = new HashMap<>();
            for (int i = 0; i < orderItems.size(); i++) {
                long jewelryId = orderItems.get(i).getJewelryId();
                Jewelry jewelry = jewelryService.findById(jewelryId);
                int quantity = orderItems.get(i).getQuantity();
                items.put(jewelry, quantity);
            }

            BigDecimal orderCost = orderItemService.calculateItemsCostByOrderId(orderId);

            request.setAttribute(RequestAttribute.ORDER, order);
            request.setAttribute(RequestAttribute.CLIENT, client);
            request.setAttribute(RequestAttribute.DISCOUNT, discount);
            request.setAttribute(RequestAttribute.TOTAL_COST, orderCost);
            request.setAttribute(RequestAttribute.ITEMS_LIST, items);

            return new Router(PagePath.SINGLE_ORDER_DETAILS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowOrderCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
