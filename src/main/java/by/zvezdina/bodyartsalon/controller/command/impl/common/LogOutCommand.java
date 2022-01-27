package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.INDEX, Router.RouterType.REDIRECT);
    }
}
