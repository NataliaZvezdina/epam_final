package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Order item service.
 */
public interface OrderItemService {

    /**
     * Find all by order id list.
     *
     * @param orderId the order id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderItem> findAllByOrderId(long orderId) throws ServiceException;

    /**
     * Calculate items cost by order id big decimal.
     *
     * @param orderId the order id
     * @return the big decimal
     * @throws ServiceException the service exception
     */
    BigDecimal calculateItemsCostByOrderId(long orderId) throws ServiceException;

    /**
     * Create order item.
     *
     * @param orderItem the order item
     * @return the order item
     * @throws ServiceException the service exception
     */
    OrderItem create(OrderItem orderItem) throws ServiceException;
}
