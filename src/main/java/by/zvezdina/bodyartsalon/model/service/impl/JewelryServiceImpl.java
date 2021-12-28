package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.JewelryDao;
import by.zvezdina.bodyartsalon.model.dao.impl.JewelryDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JewelryServiceImpl implements JewelryService {
    private static final Logger logger = LogManager.getLogger();
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
}
