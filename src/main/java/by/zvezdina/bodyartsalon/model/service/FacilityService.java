package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;

import java.util.List;
import java.util.Map;

public interface FacilityService {

    List<Facility> findAll(int page) throws ServiceException;
    Facility findById(long id) throws ServiceException;
    Facility create(Facility facility) throws ServiceException;
    Facility update(Facility facility) throws ServiceException;
    boolean deleteById(long id) throws ServiceException;
    boolean restoreById(long id) throws ServiceException;
    boolean validateInputData(Map<String, String> formData);
}
