package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;

public interface ClientService {

    Client create(Client client) throws ServiceException;
    boolean verify(long id) throws ServiceException;
    Client findById(long id) throws ServiceException;
    int findDiscountByClientId(long id) throws ServiceException;
}
