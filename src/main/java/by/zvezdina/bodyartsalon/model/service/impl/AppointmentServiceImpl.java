package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.AppointmentDao;
import by.zvezdina.bodyartsalon.model.dao.FacilityDao;
import by.zvezdina.bodyartsalon.model.dao.impl.AppointmentDaoImpl;
import by.zvezdina.bodyartsalon.model.dao.impl.FacilityDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.service.AppointmentService;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private static final Logger logger = LogManager.getLogger();
    private static AppointmentServiceImpl instance;
    private AppointmentDao appointmentDao = AppointmentDaoImpl.getInstance();

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
        Appointment appointment = null;
        try {
            appointment = appointmentDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find appointment by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found appointment by id {}: {}", id, appointment);
        return appointment;
    }

    @Override
    public List<Appointment> findAllRelevantByPiercerId(long piercerId) throws ServiceException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments = appointmentDao.findAllRelevantByPiercerId(piercerId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all appointments by piercerId "
                    + piercerId, e);
        }

        logger.log(Level.DEBUG, "All found appointments by piercerId {}: {}", piercerId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws ServiceException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments = appointmentDao.findAllByPiercerIdForCurrentDate(piercerId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all appointments by piercerId "
                    + piercerId, e);
        }

        logger.log(Level.DEBUG, "All found appointments by piercerId {} for current date: {}",
                piercerId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllRelevantByClientId(long clientId) throws ServiceException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            appointments = appointmentDao.findAllRelevantByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all appointments by clientId "
                    + clientId, e);
        }

        logger.log(Level.DEBUG, "All found appointments by clientId  {}: {}", clientId, appointments);
        return appointments;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = appointmentDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete appointment by id " + id, e);
        }

        logger.log(Level.DEBUG, "Appointment by id {} was deleted: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
