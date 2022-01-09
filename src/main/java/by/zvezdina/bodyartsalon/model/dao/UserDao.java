package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.User;

import java.util.List;

public interface UserDao {

    User findByLogin(String login) throws DaoException;
    User findByEmail(String email) throws DaoException;
    User findById(Long id) throws DaoException;
    List<User> findAll() throws DaoException;
    List<User> findAll(int page) throws DaoException;
    User update(User user) throws DaoException;
    int deleteById(Long id) throws DaoException;
    int restoreById(Long id) throws DaoException;
    int updatePassword(Long id, String password) throws DaoException;

}
