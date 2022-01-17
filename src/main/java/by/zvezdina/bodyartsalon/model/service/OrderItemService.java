package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAllByOrderId(long orderId) throws ServiceException;
    BigDecimal calculateItemsCostByOrderId(long orderId) throws ServiceException;
    OrderItem create(OrderItem orderItem) throws ServiceException;
}
