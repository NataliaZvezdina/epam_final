package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;

import java.util.List;
import java.util.Map;

public interface JewelryService {

    List<Jewelry> findAll() throws ServiceException;
    List<Jewelry> findAll(int page) throws ServiceException;
    Jewelry findById(long id) throws ServiceException;
    Jewelry create(Jewelry jewelry) throws ServiceException;
    Jewelry update(Jewelry jewelry) throws ServiceException;
    boolean deleteById(long id) throws ServiceException;
    boolean restoreById(long id) throws ServiceException;
    boolean validateInputData(Map<String, String> formData);

}
