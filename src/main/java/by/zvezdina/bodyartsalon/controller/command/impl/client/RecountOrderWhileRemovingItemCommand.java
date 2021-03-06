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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecountOrderWhileRemovingItemCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final JewelryService jewelryService = JewelryServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute(SessionAttribute.BASKET);

        Long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        Integer currentItemQuantity = basket.get(jewelryId);
        if (currentItemQuantity == 1) {
            basket.remove(jewelryId);
        } else {
            basket.put(jewelryId, --currentItemQuantity);
        }

        Set<Long> itemIdSet = basket.keySet();
        List<Jewelry> basketItems = new ArrayList<>();

        try {
            for (long id : itemIdSet) {
                Jewelry jewelry = jewelryService.findById(id);
                basketItems.add(jewelry);
            }
            int discount = (Integer) session.getAttribute(SessionAttribute.USER_DISCOUNT);
            BigDecimal totalCost = jewelryService.calculateJewelrySet(basket, discount);

            request.setAttribute(RequestAttribute.TOTAL_COST, totalCost);
            request.setAttribute(RequestAttribute.BASKET_ITEMS_LIST, basketItems);
            return new Router(PagePath.BASKET, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error to recount order while removing item from basket: ", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
