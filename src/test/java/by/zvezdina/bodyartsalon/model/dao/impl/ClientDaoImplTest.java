package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ClientDaoImplTest {

    private static final long CLIENT_ID = 5;
    private static final int EXPECTED_ROWS_UPDATED = 1;
    private static final int EXPECTED_CLIENT_DISCOUNT_VALUE = 7;
    private static final long DISCOUNT_ID_TO_UPDATE = 3;
    private final BigDecimal randomMoneyToRechargeBalance = BigDecimal.valueOf(59.99);
    private final Discount firstDiscountElement = new Discount(1, 0);
    private final Discount secondDiscountElement = new Discount(2, 5);

    @Mock
    private ClientDaoImpl clientDaoMock;
    private List<Discount> expectedDiscounts;
    private Client expectedClient;
    private Client clientToCreate;
    private Client clientToVerify;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        expectedClient = new Client.Builder()
                .clientId(5)
                .email("email@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.CLIENT)
                .userStatus(UserStatus.ACTIVE)
                .discountId(2)
                .money(BigDecimal.valueOf(55.99))
                .isVerified(true)
                .build();

        clientToCreate = new Client.Builder()
                .email("email@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.CLIENT)
                .userStatus(UserStatus.ACTIVE)
                .discountId(2)
                .money(BigDecimal.valueOf(59.99))
                .isVerified(true)
                .build();

        clientToVerify = new Client.Builder()
                .clientId(7)
                .email("someNewEmail@gmail.com")
                .firstName("Tom")
                .lastName("Tomson")
                .login("tommie")
                .password("12345678Asd")
                .role(Role.CLIENT)
                .userStatus(UserStatus.INACTIVE)
                .discountId(1)
                .money(BigDecimal.valueOf(0))
                .isVerified(false)
                .build();

        expectedDiscounts = List.of(firstDiscountElement, secondDiscountElement);
    }

    @Test
    public void findByIdTest() throws DaoException {
        when(clientDaoMock.findById(CLIENT_ID)).thenReturn(expectedClient);
        Client actual = clientDaoMock.findById(CLIENT_ID);
        assertThat(actual).isEqualTo(expectedClient);
    }

    @Test
    public void createTest() throws DaoException {
        when(clientDaoMock.create(clientToCreate)).thenReturn(expectedClient);
        Client actual = clientDaoMock.create(clientToCreate);
        assertThat(actual).isEqualTo(expectedClient);
    }

    @Test
    public void verifyTest() throws DaoException {
        when(clientDaoMock.verify(clientToVerify.getUserId()))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = clientDaoMock.verify(clientToVerify.getUserId());
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }

    @Test
    public void findDiscountByClientIdTest() throws DaoException {
        when(clientDaoMock.findDiscountByClientId(CLIENT_ID)).thenReturn(EXPECTED_CLIENT_DISCOUNT_VALUE);
        int actual = clientDaoMock.findDiscountByClientId(CLIENT_ID);
        assertThat(actual).isEqualTo(EXPECTED_CLIENT_DISCOUNT_VALUE);
    }

    @Test
    public void findAllDiscountsTest() throws DaoException {
        when(clientDaoMock.findAllDiscounts()).thenReturn(expectedDiscounts);
        List<Discount> actual = clientDaoMock.findAllDiscounts();
        assertThat(actual).containsExactly(firstDiscountElement, secondDiscountElement);
    }

    @Test
    public void updateClientDiscountTest() throws DaoException {
        when(clientDaoMock.updateClientDiscount(CLIENT_ID, DISCOUNT_ID_TO_UPDATE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = clientDaoMock.updateClientDiscount(CLIENT_ID, DISCOUNT_ID_TO_UPDATE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }

    @Test
    public void updateClientBalanceTest() throws DaoException {
        when(clientDaoMock.updateClientBalance(CLIENT_ID, randomMoneyToRechargeBalance))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = clientDaoMock.updateClientBalance(CLIENT_ID, randomMoneyToRechargeBalance);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}