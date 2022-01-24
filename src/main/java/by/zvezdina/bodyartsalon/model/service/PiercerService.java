package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;

import java.util.List;
import java.util.Map;

public interface PiercerService {

    Piercer findById(long id) throws ServiceException;
    List<Piercer> findAllActive() throws ServiceException;
    Piercer create(Piercer piercer) throws ServiceException;
    boolean validateWorkingData(Map<String, String> formData);
    boolean updateWorkingInfo(Piercer piercer) throws ServiceException;
}
