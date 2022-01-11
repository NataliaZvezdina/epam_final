package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAllByOrderId(long orderId) throws ServiceException;
    OrderItem create(OrderItem orderItem) throws ServiceException;
}
