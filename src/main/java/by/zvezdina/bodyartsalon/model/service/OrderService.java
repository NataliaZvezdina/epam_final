package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll(int page) throws ServiceException;
    List<Order> findAllByClientId(int page, long clientId) throws ServiceException;
    Order findById(long id) throws ServiceException;
    Order create(Order order) throws ServiceException;
    boolean updateStatusByOrderId(long id) throws ServiceException;
    boolean deleteById(long id) throws ServiceException;
}
