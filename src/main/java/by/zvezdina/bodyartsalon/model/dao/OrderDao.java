package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Order;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Find list of orders on defined page.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAll(int page) throws DaoException;

    /**
     * Find only orders for specified client by id.
     *
     * @param page     the page
     * @param clientId the client id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllByClientId(int page, long clientId) throws DaoException;

    /**
     * Find order by id .
     *
     * @param id the id
     * @return the order
     * @throws DaoException the dao exception
     */
    Order findById(long id) throws DaoException;

    /**
     * Create order.
     *
     * @param order the order
     * @return the order
     * @throws DaoException the dao exception
     */
    Order create(Order order) throws DaoException;

    /**
     * Update status order by id.
     *
     * @param id the id
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateStatusByOrderId(long id) throws DaoException;

    /**
     * Delete order by id.
     *
     * @param id the id
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteById(long id) throws DaoException;
}
