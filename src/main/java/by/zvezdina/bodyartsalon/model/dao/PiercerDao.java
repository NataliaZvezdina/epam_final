package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;

import java.util.List;


public interface PiercerDao {

    List<Piercer> findAllActive() throws DaoException;
    Piercer findById(long id) throws DaoException;
    Piercer create(Piercer piercer) throws DaoException;
    int updateWorkingInfo(Piercer piercer) throws DaoException;

}
