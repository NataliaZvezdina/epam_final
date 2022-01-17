package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.OrderItemDao;
import by.zvezdina.bodyartsalon.model.dao.impl.OrderItemDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.service.OrderItemService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderItemServiceImpl instance;
    private final OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();

    private OrderItemServiceImpl() {
    }

    public static OrderItemService getInstance() {
        if (instance == null) {
            instance = new OrderItemServiceImpl();
        }
        return instance;
    }

    @Override
    public List<OrderItem> findAllByOrderId(long orderId) throws ServiceException {
        List<OrderItem> ordersItems = new ArrayList<>();
        try {
            ordersItems = orderItemDao.findAllByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all orderItems by orderId "
                    + orderId, e);
        }

        logger.log(Level.DEBUG, "All found orderItems by orderId {}: {}", orderId, ordersItems);
        return ordersItems;
    }

    @Override
    public BigDecimal calculateItemsCostByOrderId(long orderId) throws ServiceException {
        BigDecimal totalCost;
        try {
            totalCost = orderItemDao.calculateItemsCostByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException("calculateItemsCostByOrderId() - Failed to calculate cost by orderId "
                    + orderId, e);
        }

        logger.log(Level.DEBUG, "Total cost of orderItems by orderId {}: {}", orderId, totalCost);
        return totalCost;
    }

    @Override
    public OrderItem create(OrderItem orderItem) throws ServiceException {
        OrderItem createdOrderItem;
        try {
            createdOrderItem = orderItemDao.create(orderItem);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create orderItem ", e);
        }
        logger.log(Level.DEBUG, "OrderItem created: {}", createdOrderItem);
        return createdOrderItem;
    }
}
