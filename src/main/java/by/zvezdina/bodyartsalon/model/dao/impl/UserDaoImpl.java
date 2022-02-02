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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_BY_ID_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, role, status, is_verified 
            FROM users 
            WHERE user_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, role, status, is_verified  
            FROM users;""";

    private static final String FIND_PAGE_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 10;

    private static final String FIND_BY_LOGIN_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, role, status, is_verified  
            FROM users 
            WHERE login = ?;""";

    private static final String FIND_BY_EMAIL_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, role, status, is_verified  
            FROM users  
            WHERE email = ?;""";

    private static final String CREATE_ADMIN_QUERY = """
            INSERT INTO users (login, password, first_name, last_name, email, role, status, is_verified) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);""";

    private static final String UPDATE_QUERY = """
            UPDATE users  
            SET login = ?, password = ?, first_name = ?, last_name = ?, email = ?, role = ?, 
                status = ?, is_verified = ? 
            WHERE user_id = ?;""";

    private static final String DELETE_BY_ID_QUERY = """
            UPDATE users 
            SET status = 'inactive' 
            WHERE user_id = ?;""";

    private static final String RESTORE_BY_ID_QUERY = """
            UPDATE users 
            SET status = 'active' 
            WHERE user_id = ?;""";

    private static final String UPDATE_PASSWORD_QUERY = """
            UPDATE users 
            SET password = ?  
            WHERE user_id = ?;""";

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
    public User createAdmin(User user) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString().toLowerCase());
            statement.setString(7, user.getUserStatus().toString().toLowerCase());
            statement.setBoolean(8, user.isVerified());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    long userId = resultSet.getLong(1);
                    user.setUserId(userId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create admin: ", e);
        }
        logger.log(Level.DEBUG, "New admin created: {}", user);
        return user;
    }

    @Override
    public User update(User user) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString().toLowerCase());
            statement.setString(7, user.getUserStatus().toString().toLowerCase());
            statement.setBoolean(8, user.isVerified());
            statement.setLong(9, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update user: ", e);
        }

        logger.log(Level.DEBUG, "User updated: {}", user);
        return user;
    }

    @Override
    public int deleteById(Long id) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteById() - Failed to delete user by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    @Override
    public int restoreById(Long id) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RESTORE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("restoreById() - Failed to restore user by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
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

    @Override
    public User findByEmail(String email) throws DaoException {
        User foundUser = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundUser = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findByEmail() - Failed to find user: ", e);
        }
        logger.log(Level.DEBUG, "Found user: {}", foundUser);
        return foundUser;
    }

    @Override
    public int updatePassword(Long id, String password) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_QUERY)) {
            statement.setString(1, password);
            statement.setLong(2, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("updatePassword() - Failed to update user's password: ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    private User extract(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .userId(resultSet.getLong(USER_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .userStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .isVerified(resultSet.getBoolean(IS_VERIFIED))
                .build();
    }
}
