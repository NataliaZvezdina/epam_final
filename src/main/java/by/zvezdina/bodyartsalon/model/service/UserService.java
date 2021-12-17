package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;
}
