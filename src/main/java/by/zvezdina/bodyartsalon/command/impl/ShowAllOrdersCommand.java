package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.Command;
import by.zvezdina.bodyartsalon.command.PagePath;
import by.zvezdina.bodyartsalon.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowAllOrdersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ORDERS, Router.RouterType.FORWARD);
    }
}