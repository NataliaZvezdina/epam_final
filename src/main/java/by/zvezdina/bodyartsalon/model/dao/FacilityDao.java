package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Facility;

import java.util.List;

/**
 * The interface Facility dao.
 */
public interface FacilityDao {

    /**
     * Find list of all facilities on defined page.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Facility> findAll(int page) throws DaoException;

    /**
     * Find facility by id.
     *
     * @param id the id
     * @return the facility
     * @throws DaoException the dao exception
     */
    Facility findById(long id) throws DaoException;

    /**
     * Create facility.
     *
     * @param facility the facility
     * @return the facility
     * @throws DaoException the dao exception
     */
    Facility create(Facility facility) throws DaoException;

    /**
     * Update facility.
     *
     * @param facility the facility
     * @return the facility
     * @throws DaoException the dao exception
     */
    Facility update(Facility facility) throws DaoException;

    /**
     * Delete facility by id
     *
     * @param id the id
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int deleteById(long id) throws DaoException;

    /**
     * Restore facility by id.
     *
     * @param id the id
     * @return the int(number of rows updated while executing statement)
     * @throws DaoException the dao exception
     */
    int restoreById(long id) throws DaoException;

}
