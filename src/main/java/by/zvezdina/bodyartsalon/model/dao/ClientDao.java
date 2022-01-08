package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface ClientDao extends BaseDao <Long, Client> {

    int verify(long id) throws DaoException;
    int findDiscountByClientId(long id) throws DaoException;
    List<Discount> findAllDiscounts() throws DaoException;
    int updateClientDiscount(long clientId, long discountId) throws DaoException;
    int updateClientBalance(long clientId, BigDecimal money) throws DaoException;
}
