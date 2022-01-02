package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class AddItemToBasketCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Long, Integer> basket = (Map<Long, Integer>) session.getAttribute(SessionAttribute.BASKET);
        if (basket == null) {
            basket = new HashMap<>();
            session.setAttribute(SessionAttribute.BASKET, basket);
        }

        Long jewelryId = Long.parseLong(request.getParameter(RequestParameter.JEWELRY_ID));
        if (!basket.containsKey(jewelryId)) {
            basket.put(jewelryId, 0);
        }
        Integer currentItemQuantity = basket.get(jewelryId);
        System.out.println("previous quantity: " + currentItemQuantity);
        basket.put(jewelryId, ++currentItemQuantity);
        basket.forEach((k, v) -> System.out.println(k + " : " + v));

        String page = request.getParameter(RequestParameter.PAGE);
        System.out.println("current page: " + page);
        int currentPageNumber = 0;
        if (page != null) {
            currentPageNumber = Integer.parseInt(page);
        } else {
            currentPageNumber = 1;
        }

        return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + currentPageNumber, Router.RouterType.FORWARD);
    }
}
