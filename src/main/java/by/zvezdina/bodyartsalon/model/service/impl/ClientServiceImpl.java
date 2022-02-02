package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.ClientDao;
import by.zvezdina.bodyartsalon.model.dao.impl.ClientDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.util.FormValidator;
import by.zvezdina.bodyartsalon.util.MailSender;
import by.zvezdina.bodyartsalon.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static final BigDecimal MAX_POSSIBLE_BALANCE = new BigDecimal("999.99");
    private static ClientServiceImpl instance;
    private final ClientDao clientDao = ClientDaoImpl.getInstance();

    private ClientServiceImpl() {
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    @Override
    public Client create(Client client) throws ServiceException {
        Client createdClient;
        client.setPassword(PasswordEncoder.encode(client.getPassword()));
        try {
            createdClient = clientDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException("Failed to create client: ", e);
        }

        MailSender mailSender = new MailSender();
        String message = "To verify account follow the link: " +
                "<a href='http://localhost:8085/bodyartsalon/controller?command=verify&id=" +
                client.getUserId() + "'>verification</a>";
        mailSender.send(client.getEmail(), message);
        return createdClient;
    }

    @Override
    public boolean verify(long id) throws ServiceException {
        int rowsUpdated;
        try {
            rowsUpdated = clientDao.verify(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to verify client by id " + id, e);
        }

        logger.log(Level.DEBUG, "Client by id {} was verified: {}", id, rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public Client findById(long id) throws ServiceException {
        Client client;
        try {
            client = clientDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to find client by id " + id, e);
        }
        logger.log(Level.DEBUG, "Found client by id {}: {}", id, client);
        return client;
    }

    @Override
    public int findDiscountByClientId(long id) throws ServiceException {
        int discount;
        try {
            discount = clientDao.findDiscountByClientId(id);

        } catch (DaoException e) {
            throw new ServiceException("Failed to find client's discount by id " + id, e);
        }
        logger.log(Level.DEBUG, "Found discount of client with id {} : {}", id, discount);
        return discount;
    }

    @Override
    public List<Discount> findAllDiscounts() throws ServiceException {
        List<Discount> discounts;
        try {
            discounts = clientDao.findAllDiscounts();
        } catch (DaoException e) {
            throw new ServiceException("Failed to find all discounts ", e);
        }

        logger.log(Level.DEBUG, "All discounts found: {}", discounts);
        return discounts;
    }

    @Override
    public boolean updateClientDiscount(long clientId, long discountId) throws ServiceException {
        int rowsUpdated;
        try {
            rowsUpdated = clientDao.updateClientDiscount(clientId, discountId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update client discount ", e);
        }

        logger.log(Level.DEBUG, "Client by id {} was updated, new discount id {} : {}",
                clientId, discountId, rowsUpdated == 1);
        return rowsUpdated == 1;
    }

    @Override
    public boolean validateMoneyToAdd(String money, BigDecimal balance) {
        FormValidator validator = FormValidator.getInstance();
        boolean isValid = validator.checkRechargedMoney(money);

        if (isValid) {
            BigDecimal moneyToAdd = new BigDecimal(money);
            isValid = moneyToAdd.compareTo(MAX_POSSIBLE_BALANCE.subtract(balance)) <= 0;
        }

        logger.log(Level.DEBUG, isValid ? "Input money {} to add are valid " :
                "Input money {} to add are not valid", money);
        return isValid;
    }

    @Override
    public boolean updateBalance(long clientId, BigDecimal money) throws ServiceException {
        int rowsUpdated;
        try {
            rowsUpdated = clientDao.updateClientBalance(clientId, money);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update client balance ", e);
        }

        logger.log(Level.DEBUG, rowsUpdated == 1 ?
                "Client by id {} was updated, money added to balance: {}" :
                "Failed to update client's balance", clientId, money);
        return rowsUpdated == 1;
    }
}
