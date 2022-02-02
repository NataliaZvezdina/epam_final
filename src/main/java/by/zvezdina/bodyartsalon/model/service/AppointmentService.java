package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The interface Appointment service.
 */
public interface AppointmentService {

    /**
     * Create appointment.
     *
     * @param appointment the appointment
     * @return the appointment
     * @throws ServiceException the service exception
     */
    Appointment create(Appointment appointment) throws ServiceException;

    /**
     * Find by id appointment.
     *
     * @param id the id
     * @return the appointment
     * @throws ServiceException the service exception
     */
    Appointment findById(long id) throws ServiceException;

    /**
     * Find all list.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Appointment> findAll(int page) throws ServiceException;

    /**
     * Find all relevant by piercer id list.
     *
     * @param piercerId the piercer id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Appointment> findAllRelevantByPiercerId(long piercerId) throws ServiceException;

    /**
     * Find all by piercer id for current date list.
     *
     * @param piercerId the piercer id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws ServiceException;

    /**
     * Find all relevant by client id list.
     *
     * @param clientId the client id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Appointment> findAllRelevantByClientId(long clientId) throws ServiceException;

    /**
     * Validate input data boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean validateInputData(Map<String, String> formData);

    /**
     * Check if occupied boolean.
     *
     * @param piercerId the piercer id
     * @param dateTime  the date time
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkIfOccupied(long piercerId, LocalDateTime dateTime) throws ServiceException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteById(long id) throws ServiceException;
}
