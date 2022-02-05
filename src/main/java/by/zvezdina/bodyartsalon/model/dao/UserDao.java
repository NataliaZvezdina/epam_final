package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao {

    /**
     * Find user by specified login.
     *
     * @param login the login
     * @return the user
     * @throws DaoException the dao exception
     */
    User findByLogin(String login) throws DaoException;

    /**
     * Find user by specified email.
     *
     * @param email the email
     * @return the user
     * @throws DaoException the dao exception
     */
    User findByEmail(String email) throws DaoException;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws DaoException the dao exception
     */
    User findById(Long id) throws DaoException;

    /**
     * Find all users.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;

    /**
     * Find all users on defined page.
     *
     * @param page the page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll(int page) throws DaoException;

    /**
     * Create admin user.
     *
     * @param user the user
     * @return the user
     * @throws DaoException the dao exception
     */
    User createAdmin(User user) throws DaoException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     * @throws DaoException the dao exception
     */
    User update(User user) throws DaoException;

    /**
     * Delete user by id.
     *
     * @param id the id
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteById(Long id) throws DaoException;

    /**
     * Restore user by id.
     *
     * @param id the id
     * @return the int
     * @throws DaoException the dao exception
     */
    int restoreById(Long id) throws DaoException;

    /**
     * Update user's password.
     *
     * @param id       the id
     * @param password the password
     * @return the int
     * @throws DaoException the dao exception
     */
    int updatePassword(Long id, String password) throws DaoException;

}
