package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Client dao.
 */
public interface ClientDao {

    /**
     * Create client.
     *
     * @param client the client
     * @return the client
     * @throws DaoException the dao exception
     */
    Client create(Client client) throws DaoException;

    /**
     * Verify client by id.
     *
     * @param id the id
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int verify(long id) throws DaoException;

    /**
     * Find client by id.
     *
     * @param id the id
     * @return the client
     * @throws DaoException the dao exception
     */
    Client findById(long id) throws DaoException;

    /**
     * Find discount value by client id.
     *
     * @param id the id
     * @return the int(discount value in percentage)
     * @throws DaoException the dao exception
     */
    int findDiscountByClientId(long id) throws DaoException;

    /**
     * Find list of all discounts.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Discount> findAllDiscounts() throws DaoException;

    /**
     * Update client discount by defined client id and new discount id.
     *
     * @param clientId   the client id
     * @param discountId the discount id
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateClientDiscount(long clientId, long discountId) throws DaoException;

    /**
     * Update client balance by defined client id and specified amount.
     *
     * @param clientId the client id
     * @param money    the money
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int updateClientBalance(long clientId, BigDecimal money) throws DaoException;
}
