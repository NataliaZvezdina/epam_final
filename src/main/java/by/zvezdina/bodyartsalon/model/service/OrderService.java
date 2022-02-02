package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Find all list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAll(int page) throws ServiceException;

    /**
     * Find all by client id list.
     *
     * @param page     the page
     * @param clientId the client id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllByClientId(int page, long clientId) throws ServiceException;

    /**
     * Find by id order.
     *
     * @param id the id
     * @return the order
     * @throws ServiceException the service exception
     */
    Order findById(long id) throws ServiceException;

    /**
     * Create order.
     *
     * @param order the order
     * @return the order
     * @throws ServiceException the service exception
     */
    Order create(Order order) throws ServiceException;

    /**
     * Update status by order id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStatusByOrderId(long id) throws ServiceException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteById(long id) throws ServiceException;
}
