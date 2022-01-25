package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.AppointmentDao;
import by.zvezdina.bodyartsalon.model.entity.Appointment;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class AppointmentDaoImpl implements AppointmentDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_QUERY = """
            INSERT INTO appointments (datetime, notes, facility_id, client_id, piercer_id) 
            VALUES (?, ?, ?, ?, ?);""";

    private static final String FIND_BY_ID_QUERY = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id
            FROM appointments
            WHERE appointment_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id
            FROM appointments
            ORDER BY datetime  
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 10;

    private static final String FIND_BY_PIERCER_ID_AND_DATETIME = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id 
            FROM appointments 
            WHERE piercer_id = ? AND datetime = ?;""";


    private static final String FIND_ALL_BY_PIERCER_ID = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id
            FROM appointments 
            WHERE piercer_id = ? AND datetime >= CURRENT_TIME() 
            ORDER BY datetime;""";

    private static final String FIND_ALL_BY_PIERCER_ID_FOR_CURRENT_DATE = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id
            FROM appointments 
            WHERE piercer_id = ? AND DATE(datetime) = CURRENT_DATE() 
            ORDER BY datetime;""";

    private static final String FIND_ALL_BY_CLIENT_ID = """
            SELECT appointment_id, datetime, notes, facility_id, client_id, piercer_id
            FROM appointments 
            WHERE client_id = ? AND datetime >= CURRENT_TIME() 
            ORDER BY datetime;""";

    private static final String DELETE_BY_ID_QUERY = """
            DELETE FROM appointments 
            WHERE appointment_id = ?;""";

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
    public Appointment findById(long id) throws DaoException {
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
    public Appointment findByPiercerIdAndDatetime(long piercerId, LocalDateTime dateTime)
            throws DaoException {
        Appointment foundAppointment = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PIERCER_ID_AND_DATETIME)) {
            statement.setLong(1, piercerId);
            statement.setTimestamp(2, Timestamp.valueOf(dateTime));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundAppointment = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findByPiercerIdAndDatetime() - Failed to find appointment: ", e);
        }

        logger.log(Level.DEBUG, "Found appointment by piercer id {} and datetime {}: {}",
                piercerId, dateTime, foundAppointment);
        return foundAppointment;
    }

    @Override
    public List<Appointment> findAll(int page) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment foundAppointment = extract(resultSet);
                    appointments.add(foundAppointment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll() - Failed to find all appointments: ", e);
        }

        logger.log(Level.DEBUG, "All found appointments: {}", appointments);
        return appointments;
    }

    @Override
    public Appointment create(Appointment appointment) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(appointment.getDateTime()));
            statement.setString(2, appointment.getNotes());
            statement.setLong(3, appointment.getFacilityId());
            statement.setLong(4, appointment.getClientId());
            statement.setLong(5, appointment.getPiercerId());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    long appointmentId = resultSet.getLong(1);
                    appointment.setAppointmentId(appointmentId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("create() - Failed to create appointment: ", e);
        }
        logger.log(Level.DEBUG, "Appointment created: {}", appointment);
        return appointment;
    }

    @Override
    public List<Appointment> findAllRelevantByPiercerId(long piercerId) throws DaoException {
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

        logger.log(Level.DEBUG, "All relevant found appointments by piercerId {}: {}",
                piercerId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllRelevantByClientId(long clientId) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_CLIENT_ID)) {
            statement.setLong(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment foundAppointment = extract(resultSet);
                    appointments.add(foundAppointment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllActualByPiercerId() - Failed to find appointments by clientId: "
                    + clientId, e);
        }

        logger.log(Level.DEBUG, "All relevant found appointments by clientId {}: {}",
                clientId, appointments);
        return appointments;
    }

    @Override
    public List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws DaoException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_PIERCER_ID_FOR_CURRENT_DATE)) {
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

        logger.log(Level.DEBUG, "All relevant found appointments by piercerId {}: {}",
                piercerId, appointments);
        return appointments;
    }

    @Override
    public int deleteById(long id) throws DaoException {
        int rowsUpdated = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteById() - Failed to delete appointment by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    private Appointment extract(ResultSet resultSet) throws SQLException {
        return new Appointment.Builder()
                .appointmentId(resultSet.getLong(APPOINTMENT_ID))
                .datetime(resultSet.getTimestamp(DATE_TIME).toLocalDateTime())
                .notes(resultSet.getString(NOTES))
                .facilityId(resultSet.getLong(FACILITY_ID))
                .clientId(resultSet.getLong(CLIENT_ID))
                .piercerId(resultSet.getLong(PIERCER_ID))
                .build();
    }
}
