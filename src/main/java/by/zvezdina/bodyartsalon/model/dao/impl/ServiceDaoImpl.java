package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.ServiceDao;
import by.zvezdina.bodyartsalon.model.entity.Service;
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

public class ServiceDaoImpl implements ServiceDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_BY_ID_QUERY = """
            SELECT service_id, name, service_description 
            FROM services
            WHERE service_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT service_id, name, service_description 
            FROM services;""";

    private static final String FIND_PAGE_QUERY = """
            SELECT service_id, name, service_description 
            FROM services 
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 5;

    private static final String CREATE_QUERY = """
            INSERT INTO services (name, service_description) 
            VALUES (?, ?);""";

    private static final String UPDATE_QUERY = """
            UPDATE serveces 
            SET name = ?, service_description = ?  
            WHERE service_id = ?;""";

    private static final String DELETE_BY_ID_QUERY = """
            DELETE FROM services 
            WHERE service_id = ?;""";

    private static ServiceDaoImpl instance;

    private ServiceDaoImpl() {
    }

    public static ServiceDao getInstance() {
        if (instance == null) {
            instance = new ServiceDaoImpl();
        }
        return instance;
    }

    @Override
    public Service findById(Long id) throws DaoException {
        Service foundService = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundService = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findById() - Failed to find feedback by id: " + id, e);
        }

        logger.log(Level.DEBUG, "Found service by id {}: {}", id, foundService);
        return foundService;
    }

    @Override
    public List<Service> findAll() throws DaoException {
        List<Service> allServices = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Service foundService = extract(resultSet);
                allServices.add(foundService);
            }
        } catch (SQLException e) {
            throw new DaoException("findAll() - Failed to find all services: ", e);
        }

        logger.log(Level.DEBUG, "All services: {}", allServices);
        return allServices;
    }

    @Override
    public List<Service> findAll(int page) throws DaoException {
        List<Service> servicesOnPage = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Service foundService = extract(resultSet);
                    servicesOnPage.add(foundService);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll(int page) - Failed to find all services on defined page " + page, e);
        }

        logger.log(Level.DEBUG, "All services on page {}: {}", page, servicesOnPage);
        return servicesOnPage;
    }

    @Override
    public void create(Service service) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create services: ", e);
        }

        logger.log(Level.DEBUG, "Service created: {}", service);
    }

    @Override
    public Service update(Service service) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("update() - Failed to update service: ", e);
        }

        logger.log(Level.DEBUG, "Service updated: {}", service);
        return service;
    }

    @Override
    public void deleteById(Long id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("delete() - Failed to delete service: ", e);
        }

        logger.log(Level.DEBUG, "Service by id {} deleted", id);
    }

    private Service extract(ResultSet resultSet) throws SQLException {
        return new Service.Builder()
                .serviceId(resultSet.getLong(SERVICE_ID))
                .name(resultSet.getString(NAME))
                .description(resultSet.getString(SERVICE_DESCRIPTION))
                .build();
    }
}
