package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemDao {

    List<OrderItem> findAllByOrderId(long orderId) throws DaoException;
    BigDecimal calculateItemsCostByOrderId(long orderId) throws DaoException;
    OrderItem create(OrderItem orderItem) throws DaoException;
}
