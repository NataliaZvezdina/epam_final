package by.zvezdina.bodyartsalon.model.dao.impl;


import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.PiercerDao;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class PiercerDaoImpl implements PiercerDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_USER_QUERY = """
            INSERT INTO users (login, password, first_name, last_name, email, role, status, is_verified)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?);""";

    private static final String CREATE_PIERCER_QUERY = """
            INSERT INTO piercers (piercer_id, photo_url, info_about)
            VALUES(?, ?, ?);""";

    private static final String UPDATE_WORKING_INFO = """
            UPDATE piercers 
            SET photo_url = ?, info_about = ? 
            WHERE piercer_id = ?;""";

    private static final String FIND_BY_ID_QUERY = """
            SELECT piercer_id, login, password, first_name, last_name, email, role, status, is_verified,
                  photo_url, info_about
            FROM users JOIN piercers
            ON users.user_id = piercers.piercer_id
            WHERE piercer_id = ?;""";

    private static final String FIND_ALL_ACTIVE_QUERY = """
            SELECT piercer_id, login, password, first_name, last_name, email, role, status, is_verified,
                   photo_url, info_about 
            FROM users JOIN piercers 
            ON users.user_id = piercers.piercer_id AND status = 'active';""";

    private static PiercerDaoImpl instance;

    private PiercerDaoImpl() {
    }

    public static PiercerDao getInstance() {
        if (instance == null) {
            instance = new PiercerDaoImpl();
        }
        return instance;
    }

    @Override
    public Piercer findById(long id) throws DaoException {
        Piercer foundPiercer = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundPiercer = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find piercer: ", e);
        }
        logger.log(Level.DEBUG, "Found piercer: {}", foundPiercer);
        return foundPiercer;
    }

    @Override
    public List<Piercer> findAllActive() throws DaoException {
        List<Piercer> allActivePiercers = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Piercer foundPiercer = extract(resultSet);
                allActivePiercers.add(foundPiercer);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all piercers: ", e);
        }

        logger.log(Level.DEBUG, "All piercers: {}", allActivePiercers);
        return allActivePiercers;
    }

    @Override
    public Piercer create(Piercer piercer) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(CREATE_USER_QUERY,
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement piercerStatement = connection.prepareStatement(CREATE_PIERCER_QUERY)) {

            try {
                connection.setAutoCommit(false);
                userStatement.setString(1, piercer.getLogin());
                userStatement.setString(2, piercer.getPassword());
                userStatement.setString(3, piercer.getFirstName());
                userStatement.setString(4, piercer.getLastName());
                userStatement.setString(5, piercer.getEmail());
                userStatement.setString(6, piercer.getRole().toString().toLowerCase());
                userStatement.setString(7, piercer.getUserStatus().toString().toLowerCase());
                userStatement.setBoolean(8, piercer.isVerified());
                userStatement.executeUpdate();

                try (ResultSet resultSet = userStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long piercerId = resultSet.getLong(1);
                        piercer.setUserId(piercerId);
                    }
                    piercerStatement.setLong(1, piercer.getUserId());
                    piercerStatement.setString(2, piercer.getPhotoUrl());
                    piercerStatement.setString(3, piercer.getInfoAbout());

                    piercerStatement.executeUpdate();
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Failed to create piercer: ", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create piercer: ", e);
        }

        logger.log(Level.DEBUG, "Piercer created: {}", piercer);
        return piercer;
    }


    @Override
    public int updateWorkingInfo(Piercer piercer) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WORKING_INFO)) {
            statement.setString(1, piercer.getPhotoUrl());
            statement.setString(2, piercer.getInfoAbout());
            statement.setLong(3, piercer.getUserId());
            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("updateWorkingInfo() - Failed to update piercer working info: ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    private Piercer extract(ResultSet resultSet) throws SQLException {
        return new Piercer.Builder()
                .piercerId(resultSet.getLong(PIERCER_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .userStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .isVerified(resultSet.getBoolean(IS_VERIFIED))
                .photoUrl(resultSet.getString(PHOTO_URL))
                .infoAbout(resultSet.getString(INFO_ABOUT))
                .build();
    }
}
