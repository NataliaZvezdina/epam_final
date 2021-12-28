package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.dao.ClientDao;
import by.zvezdina.bodyartsalon.model.dao.impl.ClientDaoImpl;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.util.MailSender;
import by.zvezdina.bodyartsalon.model.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static ClientServiceImpl instance;
    private ClientDao clientDao = ClientDaoImpl.getInstance();

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
        Client createdClient = null;

        client.setPassword(PasswordEncoder.encode(client.getPassword()));
        try {
            createdClient = clientDao.create(client);
            System.out.println(createdClient);
        } catch (DaoException e) {
            throw new ServiceException("Failed to create client: ", e);
        }

        MailSender mailSender = new MailSender();
        String message = "To verify account follow the link: " +
                "<a href='http://localhost:8080/bodyartsalon_war_exploded/controller?command=verify&id=" +
                client.getUserId() + "'>verification</a>";
        mailSender.send(client.getEmail(), message);
        return createdClient;
    }

    @Override
    public boolean verify(long id) throws ServiceException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = clientDao.verify(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to verify client by id " + id, e);
        }

        logger.log(Level.DEBUG, "Client by id {} was verified: ", rowsUpdated == 1);
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
        return client;
    }
}
