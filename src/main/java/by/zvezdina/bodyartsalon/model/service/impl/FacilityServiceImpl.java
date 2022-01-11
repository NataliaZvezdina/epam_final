package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.FacilityDao;
import by.zvezdina.bodyartsalon.model.dao.impl.FacilityDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Facility;
import by.zvezdina.bodyartsalon.model.service.FacilityService;
import by.zvezdina.bodyartsalon.util.FormValidator;
import by.zvezdina.bodyartsalon.util.XssDefender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacilityServiceImpl implements FacilityService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REPLACEMENT_FOR_INVALID_PRICE = "0";
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
    public Facility create(Facility facility) throws ServiceException {
        Facility createdFacility;
        try {
            createdFacility = facilityDao.create(facility);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create facility ", e);
        }
        logger.log(Level.DEBUG, "Facility was created: {}", createdFacility);
        return createdFacility;
    }

    @Override
    public Facility update(Facility facility) throws ServiceException {
        try {
            facilityDao.update(facility);
        } catch (DaoException e) {
            throw new ServiceException("update() - Failed to update facility ", e);
        }
        logger.log(Level.DEBUG, "Facility was updated: {}", facility);
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

    @Override
    public boolean validateInputData(Map<String, String> formData) {
        XssDefender xssDefender = XssDefender.getInstance();
        String safeName = xssDefender.safeFormData(formData.get(RequestParameter.NAME));
        formData.put(RequestParameter.NAME, safeName);

        String safeDescription = xssDefender
                .safeFormData(formData.get(RequestParameter.FACILITY_DESCRIPTION));
        formData.put(RequestParameter.FACILITY_DESCRIPTION, safeDescription);

        FormValidator validator = FormValidator.getInstance();
        boolean isDataValid = true;

        String price = formData.get(RequestParameter.FACILITY_PRICE);
        if (!validator.checkMoney(price)) {
            formData.put(RequestParameter.FACILITY_PRICE, REPLACEMENT_FOR_INVALID_PRICE);
            isDataValid = false;
        }
        return isDataValid;
    }
}
