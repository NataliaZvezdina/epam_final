package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.AppointmentDao;
import by.zvezdina.bodyartsalon.model.dao.impl.AppointmentDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.util.FormValidator;
import by.zvezdina.bodyartsalon.util.XssDefender;
import com.oracle.wls.shaded.org.apache.regexp.RE;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AppointmentServiceImpl implements AppointmentService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REPLACEMENT_FOR_INVALID_HOUR = "10";
    private static AppointmentServiceImpl instance;
    private final AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();

    private AppointmentServiceImpl() {
    }

    public static AppointmentService getInstance() {
        if (instance == null) {
            instance = new AppointmentServiceImpl();
        }
        return instance;
    }

    @Override
    public Appointment create(Appointment appointment) throws ServiceException {
        Appointment createdAppointment;
        try {
            createdAppointment = appointmentDao.create(appointment);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create appointment ", e);
        }
        logger.log(Level.DEBUG, "Appointment was created: {}", createdAppointment);
        return createdAppointment;
    }

    @Override
    public Appointment findById(long id) throws ServiceException {
        Appointment appointment;
        try {
            appointment = appointmentDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find appointment by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found appointment by id {}: {}", id, appointment);
        return appointment;
    }

    @Override
    public List<Appointment> findAll(int page) throws ServiceException {
        List<Appointment> appointments;
        try {
            appointments = appointmentDao.findAll(page);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all appointments:", e);
        }

        logger.log(Level.DEBUG, "All found appointments: {}", appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllRelevantByPiercerId(long piercerId) throws ServiceException {
        List<Appointment> appointments;
        try {
            appointments = appointmentDao.findAllRelevantByPiercerId(piercerId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all relevant appointments by piercerId "
                    + piercerId, e);
        }

        logger.log(Level.DEBUG, "All found relevant appointments by piercerId {}: {}",
                piercerId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws ServiceException {
        List<Appointment> appointments;
        try {
            appointments = appointmentDao.findAllByPiercerIdForCurrentDate(piercerId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find appointments for current date by piercerId "
                    + piercerId, e);
        }

        logger.log(Level.DEBUG, "All found appointments by piercerId {} for current date: {}",
                piercerId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllRelevantByClientId(long clientId) throws ServiceException {
        List<Appointment> appointments;
        try {
            appointments = appointmentDao.findAllRelevantByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all appointments by clientId "
                    + clientId, e);
        }

        logger.log(Level.DEBUG, "All found appointments by clientId  {}: {}",
                clientId, appointments);
        return appointments;
    }

    @Override
    public boolean validateInputData(Map<String, String> formData) {
        XssDefender xssDefender = XssDefender.getInstance();
        String safeNotes = xssDefender.safeFormData(formData.get(RequestParameter.NOTES));
        formData.put(RequestParameter.NOTES, safeNotes);

        boolean isDataValid = true;
        String dateString = formData.get(RequestParameter.DATE);
        LocalDate date = LocalDate.parse(dateString);
        if (date.isBefore(LocalDate.now())) {
            formData.put(RequestParameter.DATE, LocalDate.now().toString());
            isDataValid = false;
        }

        FormValidator validator = FormValidator.getInstance();
        String hour = formData.get(RequestParameter.HOUR);
        if (!validator.checkHour(hour)) {
            formData.put(RequestParameter.HOUR, REPLACEMENT_FOR_INVALID_HOUR);
            isDataValid = false;
        }
        logger.log(Level.DEBUG, isDataValid ? "Input appointment data are valid" :
                "Input appointment data are invalid");
        return isDataValid;
    }

    @Override
    public boolean checkIfOccupied(long piercerId, LocalDateTime dateTime) throws ServiceException {
        Appointment foundAppointment;
        try {
            foundAppointment = appointmentDao.findByPiercerIdAndDatetime(piercerId, dateTime);
        } catch (DaoException e) {
            throw new ServiceException("checkIfOccupied() - Failed to find appointment: ", e);
        }

        if (foundAppointment == null) {
            logger.log(Level.DEBUG, "Appointment by datetime {} and by piercer id {} do not exist",
                    dateTime, piercerId);
            return false;
        }
        logger.log(Level.DEBUG, "Appointment by datetime {} and by piercer id {} has been already occupied",
                dateTime, piercerId);
        return true;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        int rowsUpdated;
        try {
            rowsUpdated = appointmentDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete appointment by id " + id, e);
        }

        logger.log(Level.DEBUG, "Appointment by id {} was deleted: {}", id, rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
