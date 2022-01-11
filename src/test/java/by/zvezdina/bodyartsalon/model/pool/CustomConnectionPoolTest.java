package by.zvezdina.bodyartsalon.model.pool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Custom connection pool test.
 */
class CustomConnectionPoolTest {
    private static final int EXPECTED_POOL_SIZE = 8;

    /**
     * Test custom pool initializing.
     */
    @Test
    void connectionPoolInitializeTest() {

        CustomConnectionPool poolInstance = CustomConnectionPool.getInstance();
        int actualPoolSize = poolInstance.getFreeConnectionsNumber();

        assertEquals(EXPECTED_POOL_SIZE, actualPoolSize);
    }

}