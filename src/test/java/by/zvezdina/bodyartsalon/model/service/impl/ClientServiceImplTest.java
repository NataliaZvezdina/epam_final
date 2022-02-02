package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.entity.Discount;
import by.zvezdina.bodyartsalon.model.entity.Role;
import by.zvezdina.bodyartsalon.model.entity.UserStatus;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {

    private static final long CLIENT_ID = 7;
    private static final boolean EXPECTED = true;
    private static final int EXPECTED_CLIENT_DISCOUNT_VALUE = 0;
    private static final long DISCOUNT_ID_TO_UPDATE = 3;
    private static final String MONEY_TO_ADD = "99.99";
    private final BigDecimal toRechargeBalance = BigDecimal.valueOf(55);
    private final BigDecimal balance = BigDecimal.valueOf(555.15);
    private final Discount firstDiscountElement = new Discount(1, 0);
    private final Discount secondDiscountElement = new Discount(2, 5);

    @Mock
    private ClientService clientServiceMock;
    private List<Discount> expectedDiscounts;
    private Client expectedClient;
    private Client clientToCreate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        expectedClient = new Client.Builder()
                .clientId(3)
                .email("email@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.CLIENT)
                .userStatus(UserStatus.INACTIVE)
                .discountId(1)
                .money(BigDecimal.valueOf(0))
                .isVerified(false)
                .build();

        clientToCreate = new Client.Builder()
                .email("email@gmail.com")
                .firstName("Bob")
                .lastName("Bobson")
                .login("bobbie")
                .password("12345678Qwe")
                .role(Role.CLIENT)
                .userStatus(UserStatus.INACTIVE)
                .isVerified(false)
                .build();

        expectedDiscounts = List.of(firstDiscountElement, secondDiscountElement);
    }

    @Test
    public void createTest() throws ServiceException {
        when(clientServiceMock.create(clientToCreate)).thenReturn(expectedClient);
        Client actual = clientServiceMock.create(clientToCreate);
        assertThat(actual).isEqualTo(expectedClient);
    }

    @Test
    public void verifyTest() throws ServiceException {
        when(clientServiceMock.verify(CLIENT_ID)).thenReturn(EXPECTED);
        boolean actual = clientServiceMock.verify(CLIENT_ID);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(clientServiceMock.findById(CLIENT_ID)).thenReturn(expectedClient);
        Client actual = clientServiceMock.findById(CLIENT_ID);
        assertThat(actual).isEqualTo(expectedClient);
    }

    @Test
    public void findDiscountByClientIdTest() throws ServiceException {
        when(clientServiceMock.findDiscountByClientId(CLIENT_ID))
                .thenReturn(EXPECTED_CLIENT_DISCOUNT_VALUE);
        int actual = clientServiceMock.findDiscountByClientId(CLIENT_ID);
        assertThat(actual).isEqualTo(EXPECTED_CLIENT_DISCOUNT_VALUE);
    }

    @Test
    public void findAllDiscountsTest() throws ServiceException {
        when(clientServiceMock.findAllDiscounts()).thenReturn(expectedDiscounts);
        List<Discount> actual = clientServiceMock.findAllDiscounts();
        assertThat(actual).containsExactly(firstDiscountElement, secondDiscountElement);
    }

    @Test
    public void updateClientDiscountTest() throws ServiceException {
        when(clientServiceMock.updateClientDiscount(CLIENT_ID, DISCOUNT_ID_TO_UPDATE))
                .thenReturn(EXPECTED);
        boolean actual = clientServiceMock.updateClientDiscount(CLIENT_ID, DISCOUNT_ID_TO_UPDATE);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void validateMoneyToAddTest() {
        when(clientServiceMock.validateMoneyToAdd(MONEY_TO_ADD, balance))
                .thenReturn(EXPECTED);
        boolean actual = clientServiceMock.validateMoneyToAdd(MONEY_TO_ADD, balance);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void updateBalanceTest() throws ServiceException {
        when(clientServiceMock.updateBalance(CLIENT_ID, toRechargeBalance))
                .thenReturn(EXPECTED);
        boolean actual = clientServiceMock.updateBalance(CLIENT_ID, toRechargeBalance);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}