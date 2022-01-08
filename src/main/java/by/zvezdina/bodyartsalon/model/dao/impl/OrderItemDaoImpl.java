package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.dao.OrderItemDao;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDaoImpl implements OrderItemDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_QUERY = """
            INSERT INTO order_items (order_id, jewelry_id, quantity) 
            VALUES (?, ?, ?);""";

    private static OrderItemDaoImpl instance;

    private OrderItemDaoImpl() {
    }

    public static OrderItemDao getInstance() {
        if (instance == null) {
            instance = new OrderItemDaoImpl();
        }
        return instance;
    }

    @Override
    public OrderItem create(OrderItem orderItem) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getJewelryId());
            statement.setInt(3, orderItem.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create orderItem: ", e);
        }
        logger.log(Level.DEBUG, "OrderItem created: {}", orderItem);
        return orderItem;
    }
}
