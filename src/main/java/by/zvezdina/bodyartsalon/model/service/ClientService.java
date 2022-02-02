package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Create client.
     *
     * @param client the client
     * @return the client
     * @throws ServiceException the service exception
     */
    Client create(Client client) throws ServiceException;

    /**
     * Verify boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean verify(long id) throws ServiceException;

    /**
     * Find by id client.
     *
     * @param id the id
     * @return the client
     * @throws ServiceException the service exception
     */
    Client findById(long id) throws ServiceException;

    /**
     * Find discount by client id int.
     *
     * @param id the id
     * @return the int
     * @throws ServiceException the service exception
     */
    int findDiscountByClientId(long id) throws ServiceException;

    /**
     * Find all discounts list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Discount> findAllDiscounts() throws ServiceException;

    /**
     * Update client discount boolean.
     *
     * @param clientId   the client id
     * @param discountId the discount id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateClientDiscount(long clientId, long discountId) throws ServiceException;

    /**
     * Validate money to add boolean.
     *
     * @param money   the money
     * @param balance the balance
     * @return the boolean
     */
    boolean validateMoneyToAdd(String money, BigDecimal balance);

    /**
     * Update balance boolean.
     *
     * @param clientId the client id
     * @param money    the money
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateBalance(long clientId, BigDecimal money) throws ServiceException;
}
