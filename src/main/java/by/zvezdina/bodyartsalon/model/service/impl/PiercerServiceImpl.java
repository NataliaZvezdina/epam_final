package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.controller.command.RequestParameter;
import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.PiercerDao;
import by.zvezdina.bodyartsalon.model.dao.impl.PiercerDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.util.FormValidator;
import by.zvezdina.bodyartsalon.util.PasswordEncoder;
import by.zvezdina.bodyartsalon.util.XssDefender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class PiercerServiceImpl implements PiercerService {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
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
        Piercer piercer;
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
        List<Piercer> piercers;
        try {
            piercers = piercerDao.findAllActive();
        } catch (DaoException e) {
            throw new ServiceException("findAllActive() - Failed to find all active piercers: ", e);
        }

        logger.log(Level.DEBUG, "All found active piercers: {}", piercers);
        return piercers;
    }

    @Override
    public Piercer create(Piercer piercer) throws ServiceException {
        Piercer createdPiercer;
        piercer.setPassword(PasswordEncoder.encode(piercer.getPassword()));
        try {
            createdPiercer = piercerDao.create(piercer);
        } catch (DaoException e) {
            throw new ServiceException("Failed to create piercer: ", e);
        }
        logger.log(Level.DEBUG, "Created piercer: {}", createdPiercer);
        return createdPiercer;
    }

    @Override
    public boolean validateWorkingData(Map<String, String> formData) {
        XssDefender xssDefender = XssDefender.getInstance();
        String safeUrl = xssDefender.safeFormData(formData.get(RequestParameter.IMAGE_URL));
        formData.put(RequestParameter.IMAGE_URL, safeUrl);

        String safeInfo = xssDefender.safeFormData(formData.get(RequestParameter.INFO_ABOUT));
        formData.put(RequestParameter.INFO_ABOUT, safeInfo);

        FormValidator validator = FormValidator.getInstance();
        boolean isDataValid = true;

        if (!validator.checkImageUrl(safeUrl)) {
            formData.put(RequestParameter.IMAGE_URL, EMPTY_STRING);
            isDataValid = false;
        }

        return isDataValid;
    }

    @Override
    public boolean updateWorkingInfo(Piercer piercer) throws ServiceException {
        int rowsUpdated;
        try {
            rowsUpdated = piercerDao.updateWorkingInfo(piercer);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update piercer working info: ", e);
        }

        logger.log(Level.DEBUG, "Piercer working info was updated : {}", rowsUpdated == 1);
        return rowsUpdated == 1;
    }
}
