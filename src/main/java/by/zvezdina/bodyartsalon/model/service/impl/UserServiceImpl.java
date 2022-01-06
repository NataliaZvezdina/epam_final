package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.dao.impl.UserDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import by.zvezdina.bodyartsalon.model.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
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
}
