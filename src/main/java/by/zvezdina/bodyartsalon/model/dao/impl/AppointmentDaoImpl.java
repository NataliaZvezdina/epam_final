package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.AppointmentDao;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_BY_ID_QUERY = """
            SELECT appointment_id, datetime, notes, service_id, client_id, piercer_id 
            FROM appointments 
            WHERE appointment_id = ?;""";

    private static final String FIND_BY_DATE_QUERY = """
            SELECT appointment_id, datetime, notes, service_id, client_id, piercer_id 
            FROM appointments 
            WHERE DATE(datetime) = ?;""";

    private static final String FIND_ALL_BY_PIERCER_ID = """
            SELECT appointment_id, datetime, notes, service_id, client_id, piercer_id
            FROM appointments 
            WHERE piercer_id = ? AND DATE(datetime) >= CURRENT_DATE();""";

    private static AppointmentDaoImpl instance;

    private AppointmentDaoImpl() {
    }

    public static AppointmentDao getInstance() {
        if (instance == null) {
            instance = new AppointmentDaoImpl();
        }
        return instance;
    }

    @Override
    public Appointment findById(Long id) throws DaoException {
        Appointment foundAppointment = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundAppointment = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findById() - Failed to find appointment by id: " + id, e);
        }

        logger.log(Level.DEBUG, "Found appointment by id {}: {}", id, foundAppointment);
        return foundAppointment;
    }

    @Override
    public List<Appointment> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Appointment> findAll(int page) throws DaoException {
        return null;
    }

    @Override
    public Appointment create(Appointment appointment) throws DaoException {
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) throws DaoException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    @Override
    public List<Appointment> findAllByDate(Date date) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_DATE_QUERY)) {
            statement.setDate(1, date);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment foundAppointment = extract(resultSet);
                    appointments.add(foundAppointment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllByDate() - Failed to find appointments by date: " + date, e);
        }

        logger.log(Level.DEBUG, "All found appointments by date {}: {}", date, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllActualByPiercerId(long piercerId) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_PIERCER_ID)) {
            statement.setLong(1, piercerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment foundAppointment = extract(resultSet);
                    appointments.add(foundAppointment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllActualByPiercerId() - Failed to find appointments by piercerId: "
                    + piercerId, e);
        }

        logger.log(Level.DEBUG, "All actual found appointments by piercerId {}: {}",
                piercerId, appointments);
        return appointments;
    }

    private Appointment extract(ResultSet resultSet) throws SQLException {
        return new Appointment.Builder()
                .appointmentId(resultSet.getLong(APPOINTMENT_ID))
                .datetime(resultSet.getTimestamp(DATE_TIME))
                .notes(resultSet.getString(NOTES))
                .serviceId(resultSet.getLong(SERVICE_ID))
                .clientId(resultSet.getLong(CLIENT_ID))
                .piercerId(resultSet.getLong(PIERCER_ID))
                .build();
    }
}
