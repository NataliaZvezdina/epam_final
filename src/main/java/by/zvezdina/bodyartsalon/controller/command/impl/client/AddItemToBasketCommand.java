package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
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
        basket.put(jewelryId, ++currentItemQuantity);

        String page = request.getParameter(RequestParameter.PAGE);
        int currentPageNumber = page != null ? Integer.parseInt(page) : 1;

        return new Router(PagePath.GO_TO_JEWELRY_DEFINED_PAGE + currentPageNumber,
                Router.RouterType.FORWARD);
    }
}
