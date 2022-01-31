package by.zvezdina.bodyartsalon.model.dao.impl;

import by.zvezdina.bodyartsalon.exception.DaoException;
import by.zvezdina.bodyartsalon.model.dao.OrderItemDao;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OrderItemDaoImplTest {

    private static final long ORDER_ID_TO_FIND_ITEMS = 1;

    @Mock
    private OrderItemDao orderItemDaoMock;
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
    public void findAllByOrderIdTest() throws DaoException {
        when(orderItemDaoMock.findAllByOrderId(ORDER_ID_TO_FIND_ITEMS))
                .thenReturn(expectedOrderItemList);
        List<OrderItem> actual = orderItemDaoMock.findAllByOrderId(ORDER_ID_TO_FIND_ITEMS);
        assertThat(actual).containsExactly(firstElementOfExpectedList, secondElementOfExpectedList);
    }

    @Test
    public void calculateItemsCostByOrderIdTest() throws DaoException {
        when(orderItemDaoMock.calculateItemsCostByOrderId(ORDER_ID_TO_FIND_ITEMS))
                .thenReturn(expectedOrderCost);
        BigDecimal actual = orderItemDaoMock.calculateItemsCostByOrderId(ORDER_ID_TO_FIND_ITEMS);
        assertThat(actual).isEqualByComparingTo(expectedOrderCost);
    }

    @Test
    public void createTest() throws DaoException {
        when(orderItemDaoMock.create(toCreate)).thenReturn(expectedCreated);
        OrderItem actual = orderItemDaoMock.create(toCreate);
        assertThat(actual).isEqualTo(expectedCreated);
    }
}