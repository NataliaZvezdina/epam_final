package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        if (session.getAttribute(SessionAttribute.CURRENT_PAGE) == null) {
            return new Router(PagePath.INDEX, Router.RouterType.FORWARD);
        }

        return new Router((String) session.getAttribute(SessionAttribute.CURRENT_PAGE), Router.RouterType.FORWARD);

    }
}
