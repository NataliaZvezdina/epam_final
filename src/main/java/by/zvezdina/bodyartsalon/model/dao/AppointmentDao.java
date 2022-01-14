package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;

import java.util.List;

public interface AppointmentDao {

    Appointment findById(long id) throws DaoException;
    List<Appointment> findAllRelevantByPiercerId(long piercerId) throws DaoException;
    List<Appointment> findAllRelevantByClientId(long clientId) throws DaoException;
    List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws DaoException;
    Appointment create(Appointment appointment) throws DaoException;
    int deleteById(long id) throws DaoException;
}
