package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.OrderDao;
import by.zvezdina.bodyartsalon.model.dao.OrderItemDao;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class OrderItemDaoImpl implements OrderItemDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_ALL_BY_ORDER_ID_QUERY = """
            SELECT order_id, jewelry_id, quantity  
            FROM order_items  
            WHERE order_id = ?;""";

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
    public List<OrderItem> findAllByOrderId(long orderId) throws DaoException {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_ORDER_ID_QUERY)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem foundOrderItem = extract(resultSet);
                    orderItems.add(foundOrderItem);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll() - Failed to find all orderItems by orderId "
                    + orderId, e);
        }

        logger.log(Level.DEBUG, "All orderItems by orderId {}: {}", orderId, orderItems);
        return orderItems;
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

    private OrderItem extract(ResultSet resultSet) throws SQLException {
        return new OrderItem.Builder()
                .orderId(resultSet.getLong(ORDER_ID))
                .jewelryId(resultSet.getLong(JEWELRY_ID))
                .quantity(resultSet.getInt(QUANTITY))
                .build();
    }
}
