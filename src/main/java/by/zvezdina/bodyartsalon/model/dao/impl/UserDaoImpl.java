package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_BY_ID_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users 
            WHERE user_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users;""";

    private static final String FIND_PAGE_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 10;

    private static final String FIND_BY_LOGIN_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users 
            WHERE login = ?;""";

    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User findById(Long id) throws DaoException {
        User foundUser = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundUser = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find user: ", e);
        }
        logger.log(Level.DEBUG, "Found user: {}", foundUser);
        return foundUser;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User foundUser = extract(resultSet);
                allUsers.add(foundUser);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all users: ", e);
        }

        logger.log(Level.DEBUG, "All users: {}", allUsers);
        return allUsers;
    }

    @Override
    public List<User> findAll(int page) throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {

             statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
             statement.setInt(2, ELEMENTS_ON_PAGE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User foundUser = extract(resultSet);
                    allUsers.add(foundUser);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all users on defined page: ", e);
        }

        logger.log(Level.DEBUG, "All users on page {}: {}", page, allUsers);
        return allUsers;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    @Override
    public User findByLogin(String login) throws DaoException {
        User foundUser = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundUser = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findByLogin() - Failed to find user: ", e);
        }
        logger.log(Level.DEBUG, "Found user: {}", foundUser);
        return foundUser;
    }

    private User extract(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .userId(resultSet.getLong(USER_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .phone(resultSet.getString(PHONE))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .userStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .build();
    }
}
