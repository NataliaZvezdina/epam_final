package by.zvezdina.bodyartsalon.model.dao.impl;


import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.PiercerDao;
import by.zvezdina.bodyartsalon.model.entity.Category;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class PiercerDaoImpl implements PiercerDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_USER_QUERY = """
            INSERT INTO users (login, password, first_name, last_name, email, phone, role, status) 
            VALUES(?, ?, ?, ?, ?, ?, ?, ?);""";

    private static final String CREATE_PIERCER_QUERY = """
            INSERT INTO piercers (piercer_id, photo_url, category, rating) 
            VALUES(?, ?, ?, ?);""";

    private static final String FIND_BY_ID_QUERY = """
            SELECT piercer_id, login, password, first_name, last_name, email, phone, role, status, photo_url, 
                category, rating 
            FROM users JOIN piercers 
            ON users.user_id = piercers.piercer_id 
            WHERE piercer_id = ?;""";

    @Override
    public Piercer findById(Long id) throws DaoException {
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
            throw new DaoException("Failed to find piercer: {}", e);
        }
        logger.log(Level.DEBUG, "Found piercer: {}", foundPiercer);
        return foundPiercer;
    }

    @Override
    public List<Piercer> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Piercer> findAll(int page) throws DaoException {
        return null;
    }

    @Override
    public void create(Piercer piercer) throws DaoException {
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
                userStatement.setString(6, piercer.getPhone());
                userStatement.setString(7, piercer.getRole().name());
                userStatement.setString(8, piercer.getUserStatus().name());
                userStatement.execute();

                try (ResultSet resultSet = userStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long piercerId = resultSet.getLong(1);
                        piercer.setUserId(piercerId);
                    }
                    piercerStatement.setLong(1, piercer.getUserId());
                    piercerStatement.setString(2, piercer.getPhotoUrl());
                    piercerStatement.setString(3, piercer.getCategory().name().toLowerCase());
                    piercerStatement.setInt(4, piercer.getRating());
                    piercerStatement.execute();
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
    }

    @Override
    public Piercer update(Piercer piercer) throws DaoException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    private Piercer extract(ResultSet resultSet) throws SQLException {
        return new Piercer.Builder()
                .piercerId(resultSet.getLong(PIERCER_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .phone(resultSet.getString(PHONE))
                .category(Category.valueOf(resultSet.getString(CATEGORY).toUpperCase()))
                .photoUrl(resultSet.getString(PHOTO_URL))
                .rating(resultSet.getInt(RATING))
                .build();
    }
}
