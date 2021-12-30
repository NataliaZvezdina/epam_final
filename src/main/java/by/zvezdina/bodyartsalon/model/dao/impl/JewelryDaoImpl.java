package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
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

    private static final String DELETE_BY_ID_QUERY = """
            UPDATE jewelry 
            SET is_available = false 
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
    public int deleteById(long id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setLong(1, id);
            int rowsUpdated = statement.executeUpdate();
            logger.log(Level.DEBUG, "Number of rows updated", rowsUpdated);
            return rowsUpdated;
        } catch (SQLException e) {
            throw new DaoException("deleteById() - Failed to delete jewelry by id " + id + " : ", e);
        }
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
