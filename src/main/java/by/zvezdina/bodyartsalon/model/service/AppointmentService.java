package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AppointmentService {

    Appointment create(Appointment appointment) throws ServiceException;
    Appointment findById(long id) throws ServiceException;
    List<Appointment> findAll(int page) throws ServiceException;
    List<Appointment> findAllRelevantByPiercerId(long piercerId) throws ServiceException;
    List<Appointment> findAllByPiercerIdForCurrentDate(long piercerId) throws ServiceException;
    List<Appointment> findAllRelevantByClientId(long clientId) throws ServiceException;
    boolean validateInputData(Map<String, String> formData);
    boolean checkIfOccupied(long piercerId, LocalDateTime dateTime) throws ServiceException;
    boolean deleteById(long id) throws ServiceException;
}
