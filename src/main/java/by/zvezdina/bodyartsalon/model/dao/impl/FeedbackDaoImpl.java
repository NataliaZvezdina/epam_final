package by.zvezdina.bodyartsalon.model.dao.impl;


import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.FeedbackDao;
import by.zvezdina.bodyartsalon.model.entity.Feedback;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class FeedbackDaoImpl implements FeedbackDao {
    private static final Logger logger = LogManager.getLogger();
    private static FeedbackDaoImpl instance;

    private static final String FIND_BY_ID_QUERY = """
            SELECT feedback_id, content, created_at, piercer_id, client_id 
            FROM feedback 
            WHERE feedback_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT feedback_id, content, created_at, piercer_id, client_id 
            FROM feedback;""";

    private static final String FIND_PAGE_QUERY = """
            SELECT feedback_id, content, created_at, piercer_id, client_id 
            FROM feedback   
            LIMIT ?, ?;""";

    private static final int ELEMENTS_ON_PAGE = 10;

    private static final String CREATE_QUERY = """
            INSERT INTO feedback (content, created_at, piercer_id, client_id) 
            VALUES (?, ?, ?, ?);""";

    private static final String UPDATE_QUERY = """
            UPDATE feedback 
            SET content = ?, created_at = ?, piercer_id = ?, client_id = ? 
            WHERE feedback_id = ?;""";

    private static final String DELETE_BY_ID_QUERY = """
            DELETE FROM feedback 
            WHERE feedback_id = ?;""";

    private static final String FIND_ALL_BY_PIERCER_ID = """
            SELECT feedback_id, content, created_at, piercer_id, client_id 
            FROM feedback  
            WHERE piercer_id = ?;""";

    @Override
    public Feedback findById(Long id) throws DaoException {
        Feedback foundFeedback = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundFeedback = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find feedback by id: ", e);
        }

        logger.log(Level.DEBUG, "Found feedback: {}", foundFeedback);
        return foundFeedback;
    }

    @Override
    public List<Feedback> findAll() throws DaoException {
        List<Feedback> allFeedback = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Feedback foundFeedback = extract(resultSet);
                allFeedback.add(foundFeedback);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all feedback: ", e);
        }

        logger.log(Level.DEBUG, "All feedback: {}", allFeedback);
        return allFeedback;
    }

    @Override
    public List<Feedback> findAll(int page) throws DaoException {
        List<Feedback> allFeedback = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PAGE_QUERY)) {

            statement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            statement.setInt(2, ELEMENTS_ON_PAGE);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback foundFeedback = extract(resultSet);
                    allFeedback.add(foundFeedback);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all feedback on defined page: ", e);
        }

        logger.log(Level.DEBUG, "All feedback on page {}: {}", page, allFeedback);
        return allFeedback;
    }

    @Override
    public void create(Feedback feedback) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {

            statement.setString(1, feedback.getContent());
            statement.setDate(2, feedback.getCreatedAt());
            statement.setLong(3, feedback.getPiercerId());
            statement.setLong(4, feedback.getClientId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to create feedback: ", e);
        }

        logger.log(Level.DEBUG, "Feedback created: {}", feedback);
    }

    @Override
    public Feedback update(Feedback feedback) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, feedback.getContent());
            statement.setDate(2, feedback.getCreatedAt());
            statement.setLong(3, feedback.getPiercerId());
            statement.setLong(4, feedback.getClientId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update feedback: ", e);
        }

        logger.log(Level.DEBUG, "Feedback updated: {}", feedback);
        return feedback;
    }

    @Override
    public void deleteById(Long id) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to delete feedback: ", e);
        }
        logger.log(Level.DEBUG, "Feedback deleted by id {}", id);
    }

    @Override
    public List<Feedback> findAllByPiercerId(long id) throws DaoException {
        List<Feedback> allFeedback = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_PIERCER_ID)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback foundFeedback = extract(resultSet);
                    allFeedback.add(foundFeedback);
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Failed to find all feedback by piercer id: ", e);
        }

        logger.log(Level.DEBUG, "All feedback by piercer id {}: {}", id, allFeedback);
        return allFeedback;
    }

    private Feedback extract(ResultSet resultSet) throws SQLException {
        return new Feedback.Builder()
                .feedbackId(resultSet.getLong(FEEDBACK_ID))
                .content(resultSet.getString(CONTENT))
                .createdAt(resultSet.getDate(CREATED_AT))
                .piercerId(resultSet.getLong(PIERCER_ID))
                .clientId(resultSet.getLong(CLIENT_ID))
                .build();
    }
}
