package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.entity.OrderStatus;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_PAGE_QUERY = """
            SELECT order_id, order_date, order_status, client_id   
            FROM orders  
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 10;

    private static final String FIND_BY_ID_QUERY = """
            SELECT order_id, order_date, order_status, client_id  
            FROM orders 
            WHERE order_id = ?;""";

    private static final String CREATE_QUERY = """
            INSERT INTO orders (order_date, order_status, client_id) 
            VALUES (?, ?, ?);""";

    private static OrderDaoImpl instance;

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAll(int page) throws DaoException {
        List<Order> ordersOnPage = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order foundOrder = extract(resultSet);
                    ordersOnPage.add(foundOrder);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll(int page) - Failed to find all orders on defined page " + page, e);
        }

        logger.log(Level.DEBUG, "All orders on page {}: {}", page, ordersOnPage);
        return ordersOnPage;
    }

    @Override
    public Order findById(long id) throws DaoException {
        Order foundOrder = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundOrder = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find order by id: ", e);
        }
        logger.log(Level.DEBUG, "Found order by id {}: {}", id, foundOrder);
        return foundOrder;
    }

    @Override
    public Order create(Order order) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(order.getDate()));
            statement.setString(2, order.getStatus().toString());
            statement.setLong(3, order.getClientId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    long orderId = resultSet.getLong(1);
                    order.setOrderId(orderId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create order: ", e);
        }
        logger.log(Level.DEBUG, "Order created: {}", order);
        return order;
    }

    private Order extract(ResultSet resultSet) throws SQLException {
        return new Order.Builder()
                .orderId(resultSet.getLong(ORDER_ID))
                .date(resultSet.getDate(ORDER_DATE).toLocalDate())
                .status(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS)))
                .clientId(resultSet.getLong(CLIENT_ID))
                .build();
    }
}
