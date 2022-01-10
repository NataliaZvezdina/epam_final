package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.FacilityDao;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.dao.impl.FacilityDaoImpl;
import by.zvezdina.bodyartsalon.model.dao.impl.JewelryDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FacilityServiceImpl implements FacilityService {
    private static final Logger logger = LogManager.getLogger();
    private static FacilityServiceImpl instance;
    private FacilityDao facilityDao = FacilityDaoImpl.getInstance();

    private FacilityServiceImpl() {
    }

    public static FacilityService getInstance() {
        if (instance == null) {
            instance = new FacilityServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Facility> findAll(int page) throws ServiceException {
        List<Facility> facilities = new ArrayList<>();
        try {
            facilities = facilityDao.findAll(page);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("findAll() - Failed to find all facilities on page " + page, e);
        }

        logger.log(Level.DEBUG, "All found facilities on page {}: {}", page, facilities);
        return facilities;
    }

    @Override
    public Facility findById(long id) throws ServiceException {
        Facility facility = null;
        try {
            facility = facilityDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find facility by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found facility by id {}: {}", id, facility);
        return facility;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = facilityDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete facility by id " + id, e);
        }

        logger.log(Level.DEBUG, "Facility by id {} was deleted: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean restoreById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = facilityDao.restoreById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to restore facility by id " + id, e);
        }

        logger.log(Level.DEBUG, "Facility by id {} was restored: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
