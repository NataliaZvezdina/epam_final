package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface JewelryService {

    List<Jewelry> findAll() throws ServiceException;

}
