package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;

import java.util.List;


/**
 * The interface Piercer dao.
 */
public interface PiercerDao {

    /**
     * Find list only of active piercers.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Piercer> findAllActive() throws DaoException;

    /**
     * Find piercer by id.
     *
     * @param id the id
     * @return the piercer
     * @throws DaoException the dao exception
     */
    Piercer findById(long id) throws DaoException;

    /**
     * Create piercer.
     *
     * @param piercer the piercer
     * @return the piercer
     * @throws DaoException the dao exception
     */
    Piercer create(Piercer piercer) throws DaoException;

    /**
     * Update working information by defined piercer.
     *
     * @param piercer the piercer
     * @return the int
     * @throws DaoException the dao exception
     */
    int updateWorkingInfo(Piercer piercer) throws DaoException;

}
