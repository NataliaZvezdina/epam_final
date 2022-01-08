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

public class OrderItemServiceImpl implements OrderItemService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderItemServiceImpl instance;
    private OrderItemDao orderItemDao = OrderItemDaoImpl.getInstance();

    private OrderItemServiceImpl() {
    }

    public static OrderItemService getInstance() {
        if (instance == null) {
            instance = new OrderItemServiceImpl();
        }
        return instance;
    }

    @Override
    public OrderItem create(OrderItem orderItem) throws ServiceException {
        OrderItem createdOrderItem;
        try {
            createdOrderItem = orderItemDao.create(orderItem);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create orderItem ", e);
        }
        logger.log(Level.DEBUG, "OrderItem created: {}", orderItem);
        return orderItem;
    }
}
