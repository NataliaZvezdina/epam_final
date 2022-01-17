package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

public class ShowBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute(SessionAttribute.BASKET);
        if (basket == null) {
            basket = new HashMap<>();
            session.setAttribute(SessionAttribute.BASKET, basket);
        }

        Set<Long> itemIdSet = basket.keySet();
        List<Jewelry> basketItems = new ArrayList<>();
        try {
            for (long id : itemIdSet) {
                Jewelry jewelry = jewelryService.findById(id);
                basketItems.add(jewelry);
            }

            int discount = (Integer) session.getAttribute(SessionAttribute.USER_DISCOUNT);
//            BigDecimal totalCost = new BigDecimal(0);
//
//            for (Jewelry item: basketItems) {
//                totalCost = totalCost.add(item.getPrice()
//                        .multiply(BigDecimal.valueOf(1d - discount / 100d))
//                        .multiply(BigDecimal.valueOf(basket.get(item.getJewelryId()))));
//            }
            BigDecimal totalCost = jewelryService.calculateJewelrySet(basket, discount);

            request.setAttribute(RequestAttribute.TOTAL_COST, totalCost);
            request.setAttribute(RequestAttribute.BASKET_ITEMS_LIST, basketItems);
            return new Router(PagePath.BASKET, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute ShowBasketCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
