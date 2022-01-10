package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Facility;

import java.util.List;

public interface FacilityService {

    List<Facility> findAll(int page) throws ServiceException;
    Facility findById(long id) throws ServiceException;
    boolean deleteById(long id) throws ServiceException;
    boolean restoreById(long id) throws ServiceException;
}
