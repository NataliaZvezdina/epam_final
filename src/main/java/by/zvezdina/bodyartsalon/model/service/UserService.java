package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;
    List<User> findAll() throws ServiceException;
    User findById(Long id) throws ServiceException;
    boolean deleteById(Long id) throws ServiceException;
    boolean restoreById(Long id) throws ServiceException;
}
