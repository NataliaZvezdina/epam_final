package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;

import java.util.List;

/**
 * The interface Jewelry dao.
 */
public interface JewelryDao {

    /**
     * Find list of all jewelry.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Jewelry> findAll() throws DaoException;

    /**
     * Find list of all jewelry on defined page.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Jewelry> findAll(int page) throws DaoException;

    /**
     * Find jewelry by id .
     *
     * @param id the id
     * @return the jewelry
     * @throws DaoException the dao exception
     */
    Jewelry findById(long id) throws DaoException;

    /**
     * Create jewelry.
     *
     * @param jewelry the jewelry
     * @return the jewelry
     * @throws DaoException the dao exception
     */
    Jewelry create(Jewelry jewelry) throws DaoException;

    /**
     * Update jewelry.
     *
     * @param jewelry the jewelry
     * @return the jewelry
     * @throws DaoException the dao exception
     */
    Jewelry update(Jewelry jewelry) throws DaoException;

    /**
     * Delete jewelry by id.
     *
     * @param id the id
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int deleteById(long id) throws DaoException;

    /**
     * Restore jewelry by id.
     *
     * @param id the id
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int restoreById(long id) throws DaoException;
}
