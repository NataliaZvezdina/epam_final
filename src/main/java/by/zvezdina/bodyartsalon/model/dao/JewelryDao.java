package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;

import java.util.List;

public interface JewelryDao {

    List<Jewelry> findAll() throws DaoException;
    List<Jewelry> findAll(int page) throws DaoException;
    Jewelry findById(long id) throws DaoException;
    int deleteById(long id) throws DaoException;
    int restoreById(long id) throws DaoException;
}
