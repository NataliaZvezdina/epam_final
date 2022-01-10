package by.zvezdina.bodyartsalon.controller.command.impl;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Jewelry;
import by.zvezdina.bodyartsalon.model.service.JewelryService;
import by.zvezdina.bodyartsalon.model.service.impl.JewelryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecountOrderWhileRemovingItemCommand implements Command {
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
        Router router = null;
        try {
            for (long id : itemIdSet) {
                Jewelry jewelry = jewelryService.findById(id);
                basketItems.add(jewelry);
            }

            int discount = (Integer) session.getAttribute(SessionAttribute.USER_DISCOUNT);
            BigDecimal totalCost = new BigDecimal(0);

            for (Jewelry item: basketItems) {
                totalCost = totalCost.add(item.getPrice().multiply(BigDecimal.valueOf(1d - discount / 100d))
                        .multiply(BigDecimal.valueOf(basket.get(item.getJewelryId()))));
            }

            request.setAttribute(RequestAttribute.TOTAL_COST, totalCost.round(MathContext.DECIMAL32));
            request.setAttribute(RequestAttribute.BASKET_ITEMS_LIST, basketItems);
            router = new Router(PagePath.BASKET, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        return router;
    }
}