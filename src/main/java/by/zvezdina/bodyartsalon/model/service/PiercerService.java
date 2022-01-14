package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;

import java.util.List;

public interface PiercerService {

    Piercer findById(long id) throws ServiceException;
    List<Piercer> findAllActive() throws ServiceException;
}
