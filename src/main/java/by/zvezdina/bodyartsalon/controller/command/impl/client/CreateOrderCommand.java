package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.*;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.OrderItemService;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderItemServiceImpl;
import by.zvezdina.bodyartsalon.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Map;

public class CreateOrderCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    public static final String NEGATIVE_NOTIFICATION = "negative.notification";
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final OrderItemService orderItemService = OrderItemServiceImpl.getInstance();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String orderCost = request.getParameter(RequestParameter.ORDER_COST);
        BigDecimal cost = new BigDecimal(orderCost);

        HttpSession session = request.getSession();

        BigDecimal userBalance = (BigDecimal) session.getAttribute(SessionAttribute.USER_MONEY);

        if (userBalance.compareTo(cost) < 0) {
            request.setAttribute(RequestAttribute.NEGATIVE_NOTIFICATION, NEGATIVE_NOTIFICATION);
            return new Router(PagePath.ORDER_CREATED_NOTIFICATION, Router.RouterType.FORWARD);
        }

        long clientId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute(SessionAttribute.BASKET);

        Order order = new Order.Builder()
                .date(LocalDate.now())
                .status(OrderStatus.ACCEPTED)
                .clientId(clientId)
                .build();

        try {
            int discount = clientService.findDiscountByClientId(clientId);
            Order createdOrder = orderService.create(order);
            for (var entry : basket.entrySet()) {
                Long jewelryId = entry.getKey();
                Integer quantity = entry.getValue();
                Jewelry jewelry = jewelryService.findById(jewelryId);
                BigDecimal price = jewelry.getPrice();
                OrderItem orderItem = new OrderItem.Builder()
                        .orderId(createdOrder.getOrderId())
                        .jewelryId(jewelryId)
                        .quantity(quantity)
                        .itemPrice(price.multiply(BigDecimal.valueOf(1d - discount / 100d), MathContext.DECIMAL32))
                        .build();
                orderItemService.create(orderItem);
            }

            clientService.updateBalance(clientId, cost.negate());
            Client client = clientService.findById(clientId);
            session.setAttribute(SessionAttribute.USER_MONEY, client.getMoney());

            basket.clear();
            return new Router(PagePath.ORDER_CREATED_NOTIFICATION, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute CreateOrderCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
