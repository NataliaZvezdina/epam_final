package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.User;

public interface UserDao extends BaseDao<Long, User>{

    User findByLogin(String login) throws DaoException;
}
