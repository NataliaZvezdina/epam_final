package by.zvezdina.bodyartsalon.command.impl;

import by.zvezdina.bodyartsalon.command.Command;
import by.zvezdina.bodyartsalon.command.PagePath;
import by.zvezdina.bodyartsalon.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.INDEX, Router.RouterType.REDIRECT);
    }
}
