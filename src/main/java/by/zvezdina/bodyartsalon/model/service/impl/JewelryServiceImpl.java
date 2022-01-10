package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.dao.impl.JewelryDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.util.FormValidator;
import by.zvezdina.bodyartsalon.model.util.XssDefender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JewelryServiceImpl implements JewelryService {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
    private static final String REPLACEMENT_FOR_INVALID_PRICE = "0";
    private static JewelryServiceImpl instance;
    private JewelryDao jewelryDao = JewelryDaoImpl.getInstance();

    private JewelryServiceImpl() {
    }

    public static JewelryService getInstance() {
        if (instance == null) {
            instance = new JewelryServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Jewelry> findAll() throws ServiceException {
        List<Jewelry> allJewelry;
        try {
            allJewelry = jewelryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all jewelry", e);
        }

        logger.log(Level.DEBUG, "All found jewelry: {}", allJewelry);
        return allJewelry;
    }

    @Override
    public List<Jewelry> findAll(int page) throws ServiceException {
        List<Jewelry> jewelryOnPage = new ArrayList<>();
        try {
            jewelryOnPage = jewelryDao.findAll(page);
        } catch (DaoException e) {
            throw new ServiceException("findAll() - Failed to find all jewelry on page " + page, e);
        }

        logger.log(Level.DEBUG, "All found jewelry on page {}: {}", page, jewelryOnPage);
        return jewelryOnPage;
    }

    @Override
    public Jewelry findById(long id) throws ServiceException {
        Jewelry jewelry = null;
        try {
            jewelry = jewelryDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("findById() - Failed to find jewelry by id " + id, e);
        }

        logger.log(Level.DEBUG, "Found jewelry by id {}: {}", id, jewelry);
        return jewelry;
    }

    @Override
    public Jewelry create(Jewelry jewelry) throws ServiceException {
        Jewelry createdJewelry;
        try {
            createdJewelry = jewelryDao.create(jewelry);
        } catch (DaoException e) {
            throw new ServiceException("create() - Failed to create jewelry ", e);
        }
        logger.log(Level.DEBUG, "Jewelry was created: {}", createdJewelry);
        return createdJewelry;
    }

    @Override
    public Jewelry update(Jewelry jewelry) throws ServiceException {
        try {
            jewelryDao.update(jewelry);
        } catch (DaoException e) {
            throw new ServiceException("update() - Failed to update jewelry ", e);
        }
        logger.log(Level.DEBUG, "Jewelry was updated: {}", jewelry);
        return jewelry;
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = jewelryDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete jewelry by id " + id, e);
        }

        logger.log(Level.DEBUG, "Jewelry by id {} was deleted: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean restoreById(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = jewelryDao.restoreById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to restore jewelry by id " + id, e);
        }

        logger.log(Level.DEBUG, "Jewelry by id {} was restored: ", rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean validateInputData(Map<String, String> formData) {
        XssDefender xssDefender = XssDefender.getInstance();
        String safeType = xssDefender.safeFormData(formData.get(RequestParameter.TYPE));
        formData.put(RequestParameter.TYPE, safeType);

        String safeManufacturer = xssDefender.safeFormData(formData.get(RequestParameter.MANUFACTURER));
        formData.put(RequestParameter.MANUFACTURER, safeManufacturer);

        String safeDescription = xssDefender.safeFormData(formData.get(RequestParameter.DESCRIPTION));
        formData.put(RequestParameter.DESCRIPTION, safeDescription);

        String safeUrl = xssDefender.safeFormData(formData.get(RequestParameter.IMAGE_URL));
        formData.put(RequestParameter.IMAGE_URL, safeUrl);

        FormValidator validator = FormValidator.getInstance();
        boolean isDataValid = true;

        String url = formData.get(RequestParameter.IMAGE_URL);
        if (!validator.checkImageUrl(url)) {
            formData.put(RequestParameter.IMAGE_URL, EMPTY_STRING);
            isDataValid = false;
        }

        String price = formData.get(RequestParameter.PRICE);
        if (!validator.checkMoney(price)) {
            formData.put(RequestParameter.PRICE, REPLACEMENT_FOR_INVALID_PRICE);
            isDataValid = false;
        }

        return isDataValid;
    }
}
