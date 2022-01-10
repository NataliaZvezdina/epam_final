package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class JewelryDaoImpl implements JewelryDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_ALL_QUERY = """
            SELECT jewelry_id, type, image_url, manufacturer, jewelry_description, price, is_available  
            FROM jewelry;""";

    private static final String FIND_PAGE_QUERY = """
            SELECT jewelry_id, type, image_url, manufacturer, jewelry_description, price, is_available  
            FROM jewelry 
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 3;

    private static final String FIND_BY_ID_QUERY = """
            SELECT jewelry_id, type, image_url, manufacturer, jewelry_description, price, is_available 
            FROM jewelry 
            WHERE jewelry_id = ?;""";

    private static final String CREATE_QUERY = """
            INSERT INTO jewelry (type, image_url, manufacturer, jewelry_description, price, is_available) 
            VALUES (?, ?, ?, ?, ?, ?);""";

    private static final String UPDATE_QUERY = """
            UPDATE jewelry 
            SET type = ?, image_url = ?, manufacturer = ?, jewelry_description = ?, price = ?, is_available = ? 
            WHERE jewelry_id = ?;""";

    private static final String DELETE_BY_ID_QUERY = """
            UPDATE jewelry 
            SET is_available = false 
            WHERE jewelry_id = ?;""";

    private static final String RESTORE_BY_ID_QUERY = """
            UPDATE jewelry 
            SET is_available = true 
            WHERE jewelry_id = ?;""";

    private static JewelryDaoImpl instance;

    private JewelryDaoImpl() {
    }

    public static JewelryDao getInstance() {
        if (instance == null) {
            instance = new JewelryDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Jewelry> findAll() throws DaoException {
        List<Jewelry> allJewelry = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Jewelry foundJewelry = extract(resultSet);
                allJewelry.add(foundJewelry);
            }
        } catch (SQLException e) {
            throw new DaoException("findAll() - Failed to find all jewelry: ", e);
        }

        logger.log(Level.DEBUG, "All allJewelry: {}", allJewelry);
        return allJewelry;
    }

    @Override
    public List<Jewelry> findAll(int page) throws DaoException {
        List<Jewelry> jewelryOnPage = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Jewelry foundJewelry = extract(resultSet);
                    jewelryOnPage.add(foundJewelry);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAll(int page) - Failed to find all jewelry on defined page " + page, e);
        }

        logger.log(Level.DEBUG, "All jewelry on page {}: {}", page, jewelryOnPage);
        return jewelryOnPage;
    }

    @Override
    public Jewelry findById(long id) throws DaoException {
        Jewelry foundJewelry = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundJewelry = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find jewelry by id: ", e);
        }
        logger.log(Level.DEBUG, "Found jewelry by id {}: {}", id, foundJewelry);
        return foundJewelry;
    }

    @Override
    public Jewelry create(Jewelry jewelry) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, jewelry.getType());
            statement.setString(2, jewelry.getImageUrl());
            statement.setString(3, jewelry.getManufacturer());
            statement.setString(4, jewelry.getDescription());
            statement.setBigDecimal(5, jewelry.getPrice());
            statement.setBoolean(6, jewelry.isAvailable());

            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys();) {
                if (resultSet.next()) {
                    long jewelryId = resultSet.getLong(1);
                    jewelry.setJewelryId(jewelryId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("create() - Failed to create jewelry: ", e);
        }
        logger.log(Level.DEBUG, "Jewelry created: {}", jewelry);
        return jewelry;
    }

    @Override
    public Jewelry update(Jewelry jewelry) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, jewelry.getType());
            statement.setString(2, jewelry.getImageUrl());
            statement.setString(3, jewelry.getManufacturer());
            statement.setString(4, jewelry.getDescription());
            statement.setBigDecimal(5, jewelry.getPrice());
            statement.setBoolean(6, jewelry.isAvailable());
            statement.setLong(7, jewelry.getJewelryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update jewelry: ", e);
        }

        logger.log(Level.DEBUG, "Jewelry updated: {}", jewelry);
        return jewelry;
    }

    @Override
    public int deleteById(long id) throws DaoException {
        int rowsUpdated = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deleteById() - Failed to delete jewelry by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    @Override
    public int restoreById(long id) throws DaoException {
        int rowsUpdated = 0;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(RESTORE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("restoreById() - Failed to restore jewelry by id " + id + " : ", e);
        }
        logger.log(Level.DEBUG, "Number of rows updated: {}", rowsUpdated);
        return rowsUpdated;
    }

    private Jewelry extract(ResultSet resultSet) throws SQLException {
        return new Jewelry.Builder()
                .jewelryId(resultSet.getLong(JEWELRY_ID))
                .type(resultSet.getString(TYPE))
                .description(resultSet.getString(JEWELRY_DESCRIPTION))
                .imageUrl(resultSet.getString(IMAGE_URL))
                .manufacturer(resultSet.getString(MANUFACTURER))
                .price(resultSet.getBigDecimal(PRICE))
                .isAvailable(resultSet.getBoolean(IS_AVAILABLE))
                .build();
    }
}
