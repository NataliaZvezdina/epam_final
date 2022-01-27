package by.zvezdina.bodyartsalon.controller.command.impl.common;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.Router;

import jakarta.servlet.http.HttpServletRequest;

public class NotFoundPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
    }
}
