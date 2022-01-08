package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.dao.impl.OrderDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderServiceImpl instance;
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public Order create(Order order) throws ServiceException {
        Order createdOrder;
        try {
            createdOrder = orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create order ", e);
        }
        logger.log(Level.DEBUG, "Order created: {}", order);
        return order;
    }
}
