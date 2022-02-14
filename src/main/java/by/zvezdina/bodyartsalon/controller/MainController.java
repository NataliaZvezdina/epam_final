package by.zvezdina.bodyartsalon.controller;

import by.zvezdina.bodyartsalon.controller.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Main controller.
 */
@WebServlet(name = "mainController", urlPatterns = {"/controller"})
public class MainController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doRequest(request, response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        Router router = command.execute(request);

        switch (router.getRouterType()) {
            case FORWARD -> {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                logger.log(Level.DEBUG, "forward to {}", router.getPagePath());
            }
            case REDIRECT -> {
                response.sendRedirect(router.getPagePath());
                logger.log(Level.DEBUG, "redirect to {}", router.getPagePath());
            }
            default -> {
                logger.log(Level.ERROR, "incorrect router type: {}", router.getRouterType());
                response.sendRedirect(PagePath.ERROR_500_PAGE);
                logger.log(Level.DEBUG, "redirect to {}", router.getPagePath());
            }
        }
    }
}