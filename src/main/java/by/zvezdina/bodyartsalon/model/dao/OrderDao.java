package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Order;

import java.util.List;

public interface OrderDao {

    List<Order> findAll(int page) throws DaoException;
    List<Order> findAllByClientId(int page, long clientId) throws DaoException;
    Order findById(long id) throws DaoException;
    Order create(Order order) throws DaoException;
    int updateStatusByOrderId(long id) throws DaoException;
    int deleteById(long id) throws DaoException;
}
