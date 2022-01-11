package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    List<OrderItem> findAllByOrderId(long orderId) throws DaoException;
    OrderItem create(OrderItem orderItem) throws DaoException;
}
