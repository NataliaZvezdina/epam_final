package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Sigh in optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> sighIn(String login, String password) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     * @throws ServiceException the service exception
     */
    User findById(Long id) throws ServiceException;

    /**
     * Create admin user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User createAdmin(User user) throws ServiceException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User update(User user) throws ServiceException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteById(Long id) throws ServiceException;

    /**
     * Restore by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreById(Long id) throws ServiceException;

    /**
     * Validate input passwords boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean validateInputPasswords(Map<String, String> formData);

    /**
     * Validate user data boolean.
     *
     * @param formData the form data
     * @return the boolean
     */
    boolean validateUserData(Map<String, String> formData);

    /**
     * Check if login exist boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkIfLoginExist(String login) throws ServiceException;

    /**
     * Check if email exist boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkIfEmailExist(String email) throws ServiceException;

    /**
     * Check user password boolean.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkUserPassword(Long id, String password) throws ServiceException;

    /**
     * Update password boolean.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(Long id, String password) throws ServiceException;
}
