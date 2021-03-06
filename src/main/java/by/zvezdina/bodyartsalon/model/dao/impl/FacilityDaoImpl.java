package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.FacilityDao;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class FacilityDaoImpl implements FacilityDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_PAGE_QUERY = """
            SELECT facility_id, name, facility_description, facility_price, is_accessible   
            FROM facilities  
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 5;

    private static final String FIND_BY_ID_QUERY = """
            SELECT facility_id, name, facility_description, facility_price, is_accessible 
            FROM facilities   
            WHERE facility_id = ?;""";

    private static final String CREATE_QUERY = """
            INSERT INTO facilities (name, facility_description, facility_price, is_accessible) 
            VALUES (?, ?, ?, ?);""";

    private static final String UPDATE_QUERY = """
            UPDATE facilities  
            SET name = ?, facility_description = ?, facility_price = ?, is_accessible = ?  
            WHERE facility_id = ?;""";

    private static final String DELETE_BY_ID_QUERY = """
            UPDATE facilities  
            SET is_accessible = false 
            WHERE facility_id = ?;""";

    private static final String RESTORE_BY_ID_QUERY = """
            UPDATE facilities 
            SET is_accessible = true 
            WHERE facility_id = ?;""";

    private static FacilityDaoImpl instance;

    private FacilityDaoImpl() {
    }

    public static FacilityDao getInstance() {
        if (instance == null) {
            instance = new FacilityDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Facility> findAll(int page) throws DaoException {
        List<Facility> facilitiesOnPage = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Facility foundFacility = extract(resultSet);
                    facilitiesOnPage.add(foundFacility);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll(int page) - Failed to find all facilities on defined page " + page, e);
        }

        logger.log(Level.DEBUG, "All facilities on page {}: {}", page, facilitiesOnPage);
        return facilitiesOnPage;
    }

    @Override
    public Facility findById(long id) throws DaoException {
        Facility foundFacility = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundFacility = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find facility by id: ", e);
        }
        logger.log(Level.DEBUG, "Found facility by id {}: {}", id, foundFacility);
        return foundFacility;
    }

    @Override
    public Facility create(Facility facility) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, facility.getName());
            statement.setString(2, facility.getDescription());
            statement.setBigDecimal(3, facility.getPrice());
            statement.setBoolean(4, facility.isAccessible());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    long facilityId = resultSet.getLong(1);
                    facility.setFacilityId(facilityId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create facility: ", e);
        }
        logger.log(Level.DEBUG, "Facility created: {}", facility);
        return facility;
    }

    @Override
    public Facility update(Facility facility) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, facility.getName());
            statement.setString(2, facility.getDescription());
            statement.setBigDecimal(3, facility.getPrice());
            statement.setBoolean(4, facility.isAccessible());
            statement.setLong(5, facility.getFacilityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update facility: ", e);
        }

        logger.log(Level.DEBUG, "Facility updated: {}", facility);
        return facility;
    }

    @Override
    public int deleteById(long id) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteById() - Failed to delete facility by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    @Override
    public int restoreById(long id) throws DaoException {
        int rowsUpdated;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RESTORE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("restoreById() - Failed to restore facility by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    private Facility extract(ResultSet resultSet) throws SQLException {
        return new Facility.Builder()
                .facilityId(resultSet.getLong(FACILITY_ID))
                .name(resultSet.getString(NAME))
                .description(resultSet.getString(FACILITY_DESCRIPTION))
                .price(resultSet.getBigDecimal(FACILITY_PRICE))
                .accessible(resultSet.getBoolean(IS_ACCESSIBLE))
                .build();
    }
}
