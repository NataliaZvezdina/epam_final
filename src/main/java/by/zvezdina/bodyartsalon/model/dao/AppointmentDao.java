package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Appointment dao.
 */
public interface AppointmentDao {

    /**
     * Find appointment by specified id.
     *
     * @param id the id
     * @return the appointment
     * @throws DaoException the dao exception
     */
    Appointment findById(long id) throws DaoException;

    /**
     * Find appointment by specified piercer id and datetime.
     *
     * @param piercerId the piercer id
     * @param dateTime  the date time
     * @return the appointment
     * @throws DaoException the dao exception
     */
    Appointment findByPiercerIdAndDatetime(long piercerId, LocalDateTime dateTime) throws DaoException;

    /**
     * Find all appointments on defined page.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Appointment> findAll(int page) throws DaoException;

    /**
     * Find list of appointments from current date and further by piercer id.
     *
     * @param piercerId the piercer id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Appointment> findAllRelevantByPiercerId(long piercerId) throws DaoException;

    /**
     * Find list of appointments from current date and further by client id.
     *
     * @param clientId the client id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Appointment> findAllRelevantByClientId(long clientId) throws DaoException;

    /**
     * Find list of appointments for current date by defined piercer by his id.
     *
     * @param piercerId the piercer id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws DaoException;

    /**
     * Create appointment.
     *
     * @param appointment the appointment
     * @return the appointment
     * @throws DaoException the dao exception
     */
    Appointment create(Appointment appointment) throws DaoException;

    /**
     * Delete appointment by id.
     *
     * @param id the id
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteById(long id) throws DaoException;
}
