package by.zvezdina.bodyartsalon.model.pool;

import by.zvezdina.bodyartsalon.exception.CustomPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static final ResourceBundle bundle;
    private static final int POOL_SIZE;
    private static final String DRIVER;
    private static final String URL;
    private static final String USER_NAME;
    private static final String PASSWORD;

    private static final AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock instanceLock = new ReentrantLock(true);
    private static CustomConnectionPool instance;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenAwayConnections;

    static {
        bundle = ResourceBundle.getBundle("database");
        DRIVER = bundle.getString("driver");
        POOL_SIZE = Integer.parseInt(bundle.getString("poolsize"));
        URL = bundle.getString("url");
        USER_NAME = bundle.getString("username");
        PASSWORD = bundle.getString("password");
    }

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>();
        try {
            Class.forName(DRIVER);
            for (int i = 0; i < POOL_SIZE; i++) {
                ProxyConnection connection =
                        new ProxyConnection(DriverManager.getConnection(URL, USER_NAME, PASSWORD));
                freeConnections.offer(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.FATAL, "Failed to initialize connection pool by cause: ", e);
            throw new ExceptionInInitializerError("Failed to initialize connection pool");
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while taking connection: {}", e);
            Thread.currentThread().interrupt();
        }
        logger.log(Level.DEBUG, "Taken connection");
        return connection;
    }

    public void releaseConnection(Connection connection) throws CustomPoolException {
        if (connection.getClass() != ProxyConnection.class) {
            throw new CustomPoolException("Wrong type of connection");
        }

        givenAwayConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while releasing connection: {}", e);
            Thread.currentThread().interrupt();
        }
        logger.log(Level.DEBUG, "Released connection");
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {

            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.ERROR, "Failed to destroy pool: ", e);
            }

        }

        deregisterDrivers();
    }

    public void deregisterDrivers() {
        DriverManager.drivers().forEach(d -> {
            try {
                DriverManager.deregisterDriver(d);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error while deregistration driver: {}", e);
            }
        });
    }
}
