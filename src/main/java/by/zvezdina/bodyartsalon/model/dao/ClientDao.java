package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;

import java.util.List;

public interface ClientDao extends BaseDao <Long, Client> {

    int verify(long id) throws DaoException;
    int findDiscountByClientId(long id) throws DaoException;
    List<Discount> findAllDiscounts() throws DaoException;
    int updateClientDiscount(long clientId, long discountId) throws DaoException;
}
