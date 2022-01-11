package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.controller.command.Router;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenOrderCommand implements Command {
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
            List<OrderItem> orderItems = orderItemService.findAllByOrderId(orderId);

            Map<Jewelry, Integer> singleOrder = new HashMap<>();
            for (int i = 0; i < orderItems.size(); i++) {
                Jewelry jewelry = jewelryService.findById(orderItems.get(i).getJewelryId());
                int quantity = orderItems.get(i).getQuantity();
                singleOrder.put(jewelry, quantity);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
            // todo

        return null;
    }
}
