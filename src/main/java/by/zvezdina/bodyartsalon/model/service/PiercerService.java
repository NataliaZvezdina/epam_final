package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;

import java.util.List;
import java.util.Map;

/**
 * The interface Piercer service.
 */
public interface PiercerService {

    /**
     * Find by id piercer.
     *
     * @param id the id
     * @return the piercer
     * @throws ServiceException the service exception
     */
    Piercer findById(long id) throws ServiceException;

    /**
     * Find all active list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Piercer> findAllActive() throws ServiceException;

    /**
     * Create piercer.
     *
     * @param piercer the piercer
     * @return the piercer
     * @throws ServiceException the service exception
     */
    Piercer create(Piercer piercer) throws ServiceException;

    /**
     * Validate working data boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean validateWorkingData(Map<String, String> formData);

    /**
     * Update working info boolean.
     *
     * @param piercer the piercer
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateWorkingInfo(Piercer piercer) throws ServiceException;
}
