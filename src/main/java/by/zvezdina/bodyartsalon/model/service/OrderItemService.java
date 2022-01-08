package by.zvezdina.bodyartsalon.model.service;

import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.OrderItem;

public interface OrderItemService {

    OrderItem create(OrderItem orderItem) throws ServiceException;
}
