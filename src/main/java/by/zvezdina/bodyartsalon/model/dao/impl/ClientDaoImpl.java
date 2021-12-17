package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.ClientDao;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.pool.CustomConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.zvezdina.bodyartsalon.model.dao.TableColumnName.*;

public class ClientDaoImpl implements ClientDao {
    private static final Logger logger = LogManager.getLogger();
    private static ClientDaoImpl instance;

    private static final String CREATE_USER_QUERY = """
            INSERT INTO users (login, password, first_name, last_name, email, phone, role, status) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);""";

    private static final String CREATE_CLIENT_QUERY = """
            INSERT INTO clients (client_id, registration_date, birth_date, money, discount_id) 
            VALUES (?, ?, ?, ?, ?);""";

    private static final String FIND_BY_ID_QUERY = """
            SELECT client_id, login, password, first_name, last_name, email, phone, role, status,  
                registration_date, birth_date, money, discount_id   
            FROM users JOIN clients 
            ON users.user_id = clients.client_id 
            WHERE client_id = ?;""";

    private static final String FIND_ALL_QUERY = """
            SELECT client_id, login, password, first_name, last_name, email, phone, role, status,  
                registration_date, birth_date, money, discount_id   
            FROM users JOIN clients 
            ON users.user_id = clients.client_id;""";

    private ClientDaoImpl() {
    }

    public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDaoImpl();
        }
        return instance;
    }

    @Override
    public Client findById(Long id) throws DaoException {
        Client foundClient = null;
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foundClient = extract(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find client by id: " + id, e);
        }
        logger.log(Level.DEBUG, "Found client by id {}: {}", id, foundClient);
        return foundClient;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> allClients = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client foundClient = extract(resultSet);
                allClients.add(foundClient);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all clients: ", e);
        }

        logger.log(Level.DEBUG, "All clients: {}", allClients);
        return allClients;
    }

    @Override
    public List<Client> findAll(int page) throws DaoException {

        return null;
    }

    @Override
    public void create(Client client) throws DaoException {

        try (Connection connection = CustomConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(CREATE_USER_QUERY,
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStatement = connection.prepareStatement(CREATE_CLIENT_QUERY)) {

            try {
                connection.setAutoCommit(false);
                userStatement.setString(1, client.getLogin());
                userStatement.setString(2, client.getPassword());
                userStatement.setString(3, client.getFirstName());
                userStatement.setString(4, client.getLastName());
                userStatement.setString(5, client.getEmail());
                userStatement.setString(6, client.getPhone());
                userStatement.setString(7, client.getRole().name().toLowerCase());
                userStatement.setString(8, client.getUserStatus().name().toLowerCase());

                userStatement.executeUpdate();
                try (ResultSet resultSet = userStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        long clientId = resultSet.getLong(1);
                        client.setUserId(clientId);
                    }

                    clientStatement.setLong(1, client.getUserId());
                    clientStatement.setDate(2, client.getRegistrationDate());
                    clientStatement.setDate(3, client.getBirthDate());
                    clientStatement.setBigDecimal(4, client.getMoney());
                    clientStatement.setLong(5, client.getDiscountId());
                    clientStatement.executeUpdate();
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Failed to create client: " + client, e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to create client: " + client, e);
        }

        logger.log(Level.DEBUG, "Client created: {}", client);
    }

    @Override
    public Client update(Client client) throws DaoException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DaoException {

    }

    private Client extract(ResultSet resultSet) throws SQLException {
        return new Client.Builder()
                .clientId(resultSet.getLong(CLIENT_ID))
                .login(resultSet.getString(LOGIN))
                .password(resultSet.getString(PASSWORD))
                .firstName(resultSet.getString(FIRST_NAME))
                .lastName(resultSet.getString(LAST_NAME))
                .email(resultSet.getString(EMAIL))
                .phone(resultSet.getString(PHONE))
                .role(Role.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .userStatus(UserStatus.valueOf(resultSet.getString(STATUS).toUpperCase()))
                .registrationDate(resultSet.getDate(REGISTRATION_DATE))
                .birthDate(resultSet.getDate(BIRTH_DATE))
                .money(resultSet.getBigDecimal(MONEY))
                .discountId(resultSet.getLong(DISCOUNT_ID))
                .build();
    }
}
