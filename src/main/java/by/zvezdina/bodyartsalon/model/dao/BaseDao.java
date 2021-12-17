package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.AbstractEntity;

import java.util.List;

public interface BaseDao <K, T extends AbstractEntity> {

    T findById(K id) throws DaoException;
    List<T> findAll() throws DaoException;
    List<T> findAll(int page) throws DaoException;
    void create(T t) throws DaoException;
    T update(T t) throws DaoException;
    void deleteById(K id) throws DaoException;
}
