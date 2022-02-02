package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The interface Jewelry service.
 */
public interface JewelryService {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Jewelry> findAll() throws ServiceException;

    /**
     * Find all list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Jewelry> findAll(int page) throws ServiceException;

    /**
     * Find by id jewelry.
     *
     * @param id the id
     * @return the jewelry
     * @throws ServiceException the service exception
     */
    Jewelry findById(long id) throws ServiceException;

    /**
     * Create jewelry.
     *
     * @param jewelry the jewelry
     * @return the jewelry
     * @throws ServiceException the service exception
     */
    Jewelry create(Jewelry jewelry) throws ServiceException;

    /**
     * Update jewelry.
     *
     * @param jewelry the jewelry
     * @return the jewelry
     * @throws ServiceException the service exception
     */
    Jewelry update(Jewelry jewelry) throws ServiceException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteById(long id) throws ServiceException;

    /**
     * Restore by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreById(long id) throws ServiceException;

    /**
     * Validate input data boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean validateInputData(Map<String, String> formData);

    /**
     * Calculate jewelry set big decimal.
     *
     * @param items          the items
     * @param clientDiscount the client discount
     * @return the big decimal
     * @throws ServiceException the service exception
     */
    BigDecimal calculateJewelrySet(Map<Long, Integer> items, int clientDiscount) throws ServiceException;
}
