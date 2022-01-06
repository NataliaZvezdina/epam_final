package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;

import java.util.List;

public interface ClientService {

    Client create(Client client) throws ServiceException;
    boolean verify(long id) throws ServiceException;
    Client findById(long id) throws ServiceException;
    int findDiscountByClientId(long id) throws ServiceException;
    List<Discount> findAllDiscounts() throws ServiceException;
    boolean updateClientDiscount(long clientId, long discountId) throws ServiceException;
}
