package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.AdminDao;
import by.zvezdina.bodyartsalon.model.entity.Admin;
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


public class AdminDaoImpl implements AdminDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_BY_ID_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users 
            WHERE role = 'admin' AND user_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users 
            WHERE role = 'admin';""";

    private static final String FIND_PAGE_QUERY = """
            SELECT user_id, login, password, first_name, last_name, email, phone, role, status 
            FROM users 
            WHERE role = 'admin' 
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 5;

    private static final String CREATE_QUERY = """
            INSERT INTO users (login, password, first_name, last_name, email, phone, role, status)
            VALUES (?, ?, ?, ?, ?, ?, ? ,?);""";


    private static AdminDaoImpl instance;

    public static AdminDao getInstance() {
        if (instance == null) {
            instance = new AdminDaoImpl();
        }
        return instance;
    }

    @Override
    public Admin findById(Long id) throws DaoException {
        Admin foundAdmin = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundAdmin = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findById() - Failed to find admin by id: " + id, e);
        }
        logger.log(Level.DEBUG, "Found admin by id {}: {}", id, foundAdmin);
        return foundAdmin;
    }

    @Override
    public List<Admin> findAll() throws DaoException {
        List<Admin> allAdmins = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin foundAdmin = extract(resultSet);
                allAdmins.add(foundAdmin);
            }
        } catch (SQLException e) {
            throw new DaoException("findAll() - Failed to find all admins: ", e);
        }

        logger.log(Level.DEBUG, "All admins: {}", allAdmins);
        return allAdmins;
    }

    @Override
    public List<Admin> findAll(int page) throws DaoException {
        List<Admin> adminsOnPage = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Admin foundAdmin = extract(resultSet);
                    adminsOnPage.add(foundAdmin);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll(int page) - Failed to find all admins on defined page " + page, e);
        }

        logger.log(Level.DEBUG, "All admins on page {}: {}", page, adminsOnPage);
        return adminsOnPage;
    }

    @Override
    public Admin create(Admin admin) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getFirstName());
            statement.setString(4, admin.getLastName());
            statement.setString(5, admin.getEmail());

            statement.setString(6, admin.getRole().name().toLowerCase());
            statement.setString(7, admin.getUserStatus().name().toLowerCase());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long adminId = resultSet.getLong(1);
                    admin.setUserId(adminId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create admin: ", e);
        }

        logger.log(Level.DEBUG, "Admin created: {}", admin);
        return admin;
    }

    @Override
    public Admin update(Admin admin) throws DaoException {

        return null;
    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    private Admin extract(ResultSet resultSet) throws SQLException {
        return new Admin.Builder()
                .adminId(resultSet.getLong(USER_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .userStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .build();
    }
}
