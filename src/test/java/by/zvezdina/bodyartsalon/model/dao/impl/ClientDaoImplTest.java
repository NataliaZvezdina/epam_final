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

    private static final Client EXPECTED_CLIENT =
            new Client.Builder()
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

    private static final Client CLIENT_TO_CREATE =
            new Client.Builder()
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

    private static final Client CLIENT_TO_VERIFY =
            new Client.Builder()
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

    private static final BigDecimal RANDOM_MONEY_TO_RECHARGE_BALANCE = BigDecimal.valueOf(59.99);
    private static final int EXPECTED_ROWS_UPDATED = 1;
    private static final int EXPECTED_CLIENT_DISCOUNT_VALUE = 7;
    private static final long DISCOUNT_ID_TO_UPDATE = 3;
    private static final Discount FIRST_DISCOUNT_ELEMENT = new Discount(1, 0);
    private static final Discount SECOND_DISCOUNT_ELEMENT = new Discount(2, 5);
    private static final List<Discount> EXPECTED_DISCOUNTS =
            List.of(FIRST_DISCOUNT_ELEMENT, SECOND_DISCOUNT_ELEMENT);

    @Mock
    private ClientDaoImpl clientDaoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() throws DaoException {
    when(clientDaoMock.findById(CLIENT_ID)).thenReturn(EXPECTED_CLIENT);
    Client actual = clientDaoMock.findById(CLIENT_ID);
    assertThat(actual).isEqualTo(EXPECTED_CLIENT);
    }

    @Test
    public void createTest() throws DaoException {
        when(clientDaoMock.create(CLIENT_TO_CREATE)).thenReturn(EXPECTED_CLIENT);
        Client actual = clientDaoMock.create(CLIENT_TO_CREATE);
        assertThat(actual).isEqualTo(EXPECTED_CLIENT);
    }

    @Test
    public void verifyTest() throws DaoException {
        when(clientDaoMock.verify(CLIENT_TO_VERIFY.getUserId()))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = clientDaoMock.verify(CLIENT_TO_VERIFY.getUserId());
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
        when(clientDaoMock.findAllDiscounts()).thenReturn(EXPECTED_DISCOUNTS);
        List<Discount> actual = clientDaoMock.findAllDiscounts();
        assertThat(actual).containsExactly(FIRST_DISCOUNT_ELEMENT, SECOND_DISCOUNT_ELEMENT);
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
        when(clientDaoMock.updateClientBalance(CLIENT_ID, RANDOM_MONEY_TO_RECHARGE_BALANCE))
                .thenReturn(EXPECTED_ROWS_UPDATED);
        int actual = clientDaoMock.updateClientBalance(CLIENT_ID, RANDOM_MONEY_TO_RECHARGE_BALANCE);
        assertThat(actual).isEqualTo(EXPECTED_ROWS_UPDATED);
    }
}