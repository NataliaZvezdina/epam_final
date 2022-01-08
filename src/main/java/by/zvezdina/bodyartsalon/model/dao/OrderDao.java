package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Order;

public interface OrderDao {

    Order create(Order order) throws DaoException;
}
