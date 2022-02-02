package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;

import java.util.List;
import java.util.Map;

/**
 * The interface Facility service.
 */
public interface FacilityService {

    /**
     * Find all list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Facility> findAll(int page) throws ServiceException;

    /**
     * Find by id facility.
     *
     * @param id the id
     * @return the facility
     * @throws ServiceException the service exception
     */
    Facility findById(long id) throws ServiceException;

    /**
     * Create facility.
     *
     * @param facility the facility
     * @return the facility
     * @throws ServiceException the service exception
     */
    Facility create(Facility facility) throws ServiceException;

    /**
     * Update facility.
     *
     * @param facility the facility
     * @return the facility
     * @throws ServiceException the service exception
     */
    Facility update(Facility facility) throws ServiceException;

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
}
