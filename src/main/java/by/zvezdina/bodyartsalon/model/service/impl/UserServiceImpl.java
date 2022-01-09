package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.dao.impl.UserDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.util.FormValidator;
import by.zvezdina.bodyartsalon.model.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
    private static UserServiceImpl instance;
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        User foundUser;
        try {
            foundUser = userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("login() - Failed to find user by login: ", e);
        }

        if (foundUser == null) {
            return Optional.empty();
        }

        String passwordToCheck = foundUser.getPassword();
        if (!passwordToCheck.equals(PasswordEncoder.encode(password))) {
            return Optional.empty();
        }

        return Optional.of(foundUser);
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> allUsers;
        try {
            allUsers = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to all users: ", e);
        }

        logger.log(Level.DEBUG, "All users found: {}", allUsers);
        return allUsers;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        User user;
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find user by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found user by id {}: {}", id, user);
        return user;
    }

    @Override
    public User update(User user) throws ServiceException {
        User updatedUser;
        try {
            updatedUser = userDao.update(user);
        } catch (DaoException e) {
            System.out.println("-----exception");
            e.printStackTrace();
            throw new ServiceException("update() - Failed to update ", e);
        }

        logger.log(Level.DEBUG, "Updated user: {}", updatedUser);
        return updatedUser;
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = userDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("deleteById() - Failed to delete user by id: ", e);
        }

        logger.log(Level.DEBUG, "User by id {} was deleted: {}", id,rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean restoreById(Long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = userDao.restoreById(id);
        } catch (DaoException e) {
            throw new ServiceException("restoreById() - Failed to restore user by id: ", e);
        }

        logger.log(Level.DEBUG, "User by id {} was restored: {}", id,rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean validateInputPasswords(Map<String, String> formData) {
        FormValidator validator = FormValidator.getInstance();
        boolean isDataValid = true;

        String oldPassword = formData.get(RequestParameter.OLD_PASSWORD);
        if (!validator.checkPassword(oldPassword)) {
            formData.put(RequestParameter.OLD_PASSWORD, EMPTY_STRING);
            isDataValid = false;
        }

        String newPassword = formData.get(RequestParameter.NEW_PASSWORD);
        if (!validator.checkPassword(newPassword)) {
            formData.put(RequestParameter.NEW_PASSWORD, EMPTY_STRING);
            isDataValid = false;
        }

        return isDataValid;
    }

    @Override
    public boolean validateUserData(Map<String, String> formData) {
        FormValidator validator = FormValidator.getInstance();
        boolean isDataValid = true;

        String firstName = formData.get(RequestParameter.FIRST_NAME);
        if (!validator.checkFirstName(firstName)) {
            formData.put(RequestParameter.FIRST_NAME, EMPTY_STRING);
            isDataValid = false;
        }

        String lastName = formData.get(RequestParameter.LAST_NAME);
        if (!validator.checkLastName(lastName)) {
            formData.put(RequestParameter.LAST_NAME, EMPTY_STRING);
            isDataValid = false;
        }

        String login = formData.get(RequestParameter.LOGIN);
        if (!validator.checkLogin(login)) {
            formData.put(RequestParameter.LOGIN, EMPTY_STRING);
            isDataValid = false;
        }

        String email = formData.get(RequestParameter.EMAIL);
        if (!validator.checkEmail(email)) {
            formData.put(RequestParameter.EMAIL, EMPTY_STRING);
            isDataValid = false;
        }

        return isDataValid;
    }

    @Override
    public boolean checkIfLoginExist(String login) throws ServiceException {
        User foundUser;
        try {
            foundUser = userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("checkIfLoginExist() - Failed to find user by login: ", e);
        }

        if (foundUser == null) {
            logger.log(Level.DEBUG,"User with login {} do not exist, login is free", login);
            return false;
        }
        logger.log(Level.DEBUG,"User with login {} already exist", login);
        return true;
    }

    @Override
    public boolean checkIfEmailExist(String email) throws ServiceException {
        User foundUser;
        try {
            foundUser = userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("checkIfEmailExist() - Failed to find user by email: ", e);
        }

        if (foundUser == null) {
            logger.log(Level.DEBUG,"User with email {} do not exist, email is free", email);
            return false;
        }
        logger.log(Level.DEBUG,"User with email {} already exist", email);
        return true;
    }

    @Override
    public boolean checkUserPassword(Long id, String password) throws ServiceException {
        User user = null;
        try {
            user = userDao.findById(id);
            System.out.println(user);
        } catch (DaoException e) {
            throw new ServiceException("checkUserPassword() - Failed to check user's password': ", e);
        }
        String realPassword = user.getPassword();
        String passwordToCheck = PasswordEncoder.encode(password);

        System.out.println("found user: " + user);
        System.out.println("real password vs password to check " + realPassword.equals(passwordToCheck));

        return realPassword.equals(passwordToCheck);
    }

    @Override
    public boolean updatePassword(Long id, String password) throws ServiceException {
        String passwordToUpdate = PasswordEncoder.encode(password);
        int rowsUpdated = 0;
        try {
            rowsUpdated = userDao.updatePassword(id, passwordToUpdate);

        } catch (DaoException e) {
            throw new ServiceException("updatePassword() - Failed to update user's password': ", e);
        }
        logger.log(Level.DEBUG, "User by id {} updated password: {}", id,rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
