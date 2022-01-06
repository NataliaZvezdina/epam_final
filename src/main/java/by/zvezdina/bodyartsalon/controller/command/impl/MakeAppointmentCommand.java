package by.zvezdina.bodyartsalon.controller.command.impl;

import by.zvezdina.bodyartsalon.controller.command.Command;
import by.zvezdina.bodyartsalon.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class MakeAppointmentCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
