package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Order item dao.
 */
public interface OrderItemDao {

    /**
     * Find only orderItems in specified order by order id.
     *
     * @param orderId the order id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderItem> findAllByOrderId(long orderId) throws DaoException;

    /**
     * Calculate items cost in defined order by order id.
     *
     * @param orderId the order id
     * @return the big decimal
     * @throws DaoException the dao exception
     */
    BigDecimal calculateItemsCostByOrderId(long orderId) throws DaoException;

    /**
     * Create order item.
     *
     * @param orderItem the order item
     * @return the order item
     * @throws DaoException the dao exception
     */
    OrderItem create(OrderItem orderItem) throws DaoException;
}
