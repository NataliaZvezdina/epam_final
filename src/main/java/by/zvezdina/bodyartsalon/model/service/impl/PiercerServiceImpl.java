package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.PiercerDao;
import by.zvezdina.bodyartsalon.model.dao.impl.PiercerDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PiercerServiceImpl implements PiercerService {
    private static final Logger logger = LogManager.getLogger();
    private static PiercerServiceImpl instance;
    private final PiercerDao piercerDao = PiercerDaoImpl.getInstance();

    private PiercerServiceImpl() {
    }

    public static PiercerServiceImpl getInstance() {
        if (instance == null) {
            instance = new PiercerServiceImpl();
        }
        return instance;
    }

    @Override
    public Piercer findById(long id) throws ServiceException {
        Piercer piercer = null;
        try {
            piercer = piercerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find piercer by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found piercer by id {}: {}", id, piercer);
        return piercer;
    }

    @Override
    public List<Piercer> findAllActive() throws ServiceException {
        List<Piercer> piercers = new ArrayList<>();
        try {
            piercers = piercerDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException("findAllActive() - Failed to find all active piercers ", e);
        }

        logger.log(Level.DEBUG, "All found active piercers: {}", piercers);
        return piercers;
    }
}
