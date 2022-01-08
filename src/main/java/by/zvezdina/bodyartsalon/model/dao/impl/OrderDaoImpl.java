package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

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
}
