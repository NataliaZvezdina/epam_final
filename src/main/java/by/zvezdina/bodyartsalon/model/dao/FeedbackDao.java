package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Feedback;

import java.util.List;

public interface FeedbackDao extends BaseDao<Long, Feedback> {

    List<Feedback> findAllByPiercerId(long id) throws DaoException;
}
