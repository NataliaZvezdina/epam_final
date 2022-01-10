package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Facility;

import java.util.List;

public interface FacilityDao {

    List<Facility> findAll(int page) throws DaoException;
    Facility findById(long id) throws DaoException;
    Facility create(Facility facility) throws DaoException;
    Facility update(Facility facility) throws DaoException;
    int deleteById(long id) throws DaoException;
    int restoreById(long id) throws DaoException;

}
