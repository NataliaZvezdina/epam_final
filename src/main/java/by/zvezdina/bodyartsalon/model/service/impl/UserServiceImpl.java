package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.UserDao;
import by.zvezdina.bodyartsalon.model.dao.impl.UserDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.User;
import by.zvezdina.bodyartsalon.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        if (!passwordToCheck.equals(password)) {
            return Optional.empty();
        }

        return Optional.of(foundUser);
    }
}
