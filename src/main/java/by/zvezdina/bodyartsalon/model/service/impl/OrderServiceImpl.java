package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.dao.impl.OrderDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderServiceImpl instance;
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAll(int page) throws ServiceException {
        List<Order> ordersOnPage = new ArrayList<>();
        try {
            ordersOnPage = orderDao.findAll(page);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all orders on page " + page, e);
        }

        logger.log(Level.DEBUG, "All found orders on page {}: {}", page, ordersOnPage);
        return ordersOnPage;
    }

    @Override
    public List<Order> findAllByClientId(int page, long clientId) throws ServiceException {
        List<Order> cclientOrders = new ArrayList<>();
        try {
            cclientOrders = orderDao.findAllByClientId(page, clientId);
        } catch (DaoException e) {
            throw new ServiceException("findAllByClientId() - Failed to find all client orders: ", e);
        }

        logger.log(Level.DEBUG, "All found orders for client by id {}: {}", clientId, cclientOrders);
        return cclientOrders;
    }

    @Override
    public Order findById(long id) throws ServiceException {
        Order order = null;
        try {
            order = orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find order by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found order by id {}: {}", id, order);
        return order;
    }

    @Override
    public Order create(Order order) throws ServiceException {
        Order createdOrder;
        try {
            createdOrder = orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create order ", e);
        }
        logger.log(Level.DEBUG, "Order created: {}", createdOrder);
        return createdOrder;
    }

    @Override
    public boolean updateStatusByOrderId(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = orderDao.updateStatusByOrderId(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update order status by id " + id, e);
        }

        logger.log(Level.DEBUG, "Order by id {} was updated: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = orderDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete order by id " + id, e);
        }

        logger.log(Level.DEBUG, "Order by id {} was deleted: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
