package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Order;
import by.zvezdina.bodyartsalon.model.entity.OrderStatus;
import by.zvezdina.bodyartsalon.model.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    private static final int DEFINED_PAGE = 1;
    private static final long CLIENT_ID = 1;
    private static final long ORDER_ID_TO_FIND = 1;
    private static final long ORDER_ID_TO_UPDATE_STATUS = 3;
    private static final long ORDER_ID_TO_DELETE = 2;
    private static final boolean EXPECTED = true;

    @Mock
    private OrderService orderServiceMock;
    private List<Order> expectedOrderList;
    private Order firstElementOfExpectedList;
    private Order secondElementOfExpectedList;
    private Order toCreate;
    private Order expectedCreated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        firstElementOfExpectedList = new Order.Builder()
                .orderId(1)
                .date(LocalDate.of(2021, 7, 13))
                .status(OrderStatus.ACCEPTED)
                .clientId(1)
                .build();

        secondElementOfExpectedList = new Order.Builder()
                .orderId(2)
                .date(LocalDate.of(2021, 6, 3))
                .status(OrderStatus.ACCEPTED)
                .clientId(1)
                .build();

        toCreate = new Order.Builder()
                .date(LocalDate.of(2021, 7, 7))
                .status(OrderStatus.ACCEPTED)
                .clientId(2)
                .build();

        expectedCreated = new Order.Builder()
                .orderId(3)
                .date(LocalDate.of(2021, 7, 7))
                .status(OrderStatus.ACCEPTED)
                .clientId(2)
                .build();

        expectedOrderList = List.of(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllTest() throws ServiceException {
        when(orderServiceMock.findAll(DEFINED_PAGE)).thenReturn(expectedOrderList);
        List<Order> actual = orderServiceMock.findAll(DEFINED_PAGE);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllByClientIdTest() throws ServiceException {
        when(orderServiceMock.findAllByClientId(DEFINED_PAGE, CLIENT_ID))
                .thenReturn(expectedOrderList);
        List<Order> actual = orderServiceMock.findAllByClientId(DEFINED_PAGE, CLIENT_ID);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        when(orderServiceMock.findById(ORDER_ID_TO_FIND))
                .thenReturn(firstElementOfExpectedList);
        Order actual = orderServiceMock.findById(ORDER_ID_TO_FIND);
        assertThat(actual).isEqualTo(firstElementOfExpectedList);
    }

    @Test
    public void createTest() throws ServiceException {
        when(orderServiceMock.create(toCreate)).thenReturn(expectedCreated);
        Order actual = orderServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }

    @Test
    public void updateStatusByOrderIdTest() throws ServiceException {
        when(orderServiceMock.updateStatusByOrderId(ORDER_ID_TO_UPDATE_STATUS))
                .thenReturn(EXPECTED);
        boolean actual = orderServiceMock.updateStatusByOrderId(ORDER_ID_TO_UPDATE_STATUS);
        assertThat(actual).isEqualTo(EXPECTED);
    }

    @Test
    public void deleteByIdTest() throws ServiceException {
        when(orderServiceMock.deleteById(ORDER_ID_TO_DELETE)).thenReturn(EXPECTED);
        boolean actual = orderServiceMock.deleteById(ORDER_ID_TO_DELETE);
        assertThat(actual).isEqualTo(EXPECTED);
    }
}