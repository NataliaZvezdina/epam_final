package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Client;


public interface ClientDao extends BaseDao <Long, Client> {

    int verify(long id) throws DaoException;
    int findDiscountByClientId(long id) throws DaoException;
}
