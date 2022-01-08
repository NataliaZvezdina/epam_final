package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

public interface OrderItemDao {

    OrderItem create(OrderItem orderItem) throws DaoException;
}
