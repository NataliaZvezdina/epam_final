package by.zvezdina.bodyartsalon.model.dao;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao extends BaseDao <Long, Appointment> {

    List<Appointment> findAllByDate(Date date) throws DaoException;
    List<Appointment> findAllActualByPiercerId(long piercerId) throws DaoException;
}
