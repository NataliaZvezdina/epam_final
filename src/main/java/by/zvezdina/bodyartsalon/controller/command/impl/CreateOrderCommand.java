package by.zvezdina.bodyartsalon.controller.command.impl;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.PagePath;
import by.zvezdina.bodyartsalon.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateOrderCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {

        logger.log(Level.DEBUG, "Inside CreateOrderCommand       Yeah!!");
        return new Router(PagePath.ORDER_CREATED_NOTIFICATION, Router.RouterType.FORWARD);
    }
}
