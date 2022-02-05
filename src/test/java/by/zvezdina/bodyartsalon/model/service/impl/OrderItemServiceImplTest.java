package by.zvezdina.bodyartsalon.model.service.impl;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import by.zvezdina.bodyartsalon.model.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OrderItemServiceImplTest {

    private static final long ORDER_ID_TO_FIND_ITEMS = 1;

    @Mock
    private OrderItemService orderItemServiceMock;
    private List<OrderItem> expectedOrderItemList;
    private OrderItem firstElementOfExpectedList;
    private OrderItem secondElementOfExpectedList;
    private BigDecimal expectedOrderCost = BigDecimal.valueOf(53.1);
    private OrderItem toCreate;
    private OrderItem expectedCreated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        firstElementOfExpectedList = new OrderItem.Builder()
                .orderId(1)
                .jewelryId(2)
                .quantity(3)
                .itemPrice(BigDecimal.valueOf(15.4))
                .build();

        secondElementOfExpectedList = new OrderItem.Builder()
                .orderId(1)
                .jewelryId(3)
                .quantity(1)
                .itemPrice(BigDecimal.valueOf(22.3))
                .build();

        toCreate = new OrderItem.Builder()
                .orderId(2)
                .jewelryId(5)
                .quantity(1)
                .itemPrice(BigDecimal.valueOf(28.4))
                .build();

        expectedCreated = new OrderItem.Builder()
                .orderId(2)
                .jewelryId(5)
                .quantity(1)
                .itemPrice(BigDecimal.valueOf(28.4))
                .build();

        expectedOrderItemList = List.of(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void findAllByOrderIdTest() throws ServiceException {
        when(orderItemServiceMock.findAllByOrderId(ORDER_ID_TO_FIND_ITEMS))
                .thenReturn(expectedOrderItemList);
        List<OrderItem> actual = orderItemServiceMock.findAllByOrderId(ORDER_ID_TO_FIND_ITEMS);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void calculateItemsCostByOrderIdTest() throws ServiceException {
        when(orderItemServiceMock.calculateItemsCostByOrderId(ORDER_ID_TO_FIND_ITEMS))
                .thenReturn(expectedOrderCost);
        BigDecimal actual = orderItemServiceMock.calculateItemsCostByOrderId(ORDER_ID_TO_FIND_ITEMS);
        assertThat(actual).isEqualByComparingTo(expectedOrderCost);
    }

    @Test
    public void createTest() throws ServiceException {
        when(orderItemServiceMock.create(toCreate)).thenReturn(expectedCreated);
        OrderItem actual = orderItemServiceMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }
}