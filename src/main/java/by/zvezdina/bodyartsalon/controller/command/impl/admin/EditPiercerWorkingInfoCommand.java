package by.zvezdina.bodyartsalon.controller.command.impl.admin;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Piercer;
import by.zvezdina.bodyartsalon.model.service.PiercerService;
import by.zvezdina.bodyartsalon.model.service.impl.PiercerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EditPiercerWorkingInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_INPUT = "inputData.invalid";
    private final PiercerService piercerService = PiercerServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String imageUrl = request.getParameter(RequestParameter.IMAGE_URL);
        String infoAbout = request.getParameter(RequestParameter.INFO_ABOUT).strip();

        Map<String, String> formData = new HashMap<>();
        formData.put(RequestParameter.IMAGE_URL, imageUrl);
        formData.put(RequestParameter.INFO_ABOUT, infoAbout);

        boolean isFormValid = piercerService.validateWorkingData(formData);

        long piercerId = Long.parseLong(request.getParameter(RequestParameter.PIERCER_ID));
        Piercer piercerToUpdate;
        try {
            piercerToUpdate = piercerService.findById(piercerId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to find piercer to update: ", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }

        piercerToUpdate.setInfoAbout(formData.get(RequestParameter.INFO_ABOUT));
        piercerToUpdate.setPhotoUrl(formData.get(RequestParameter.IMAGE_URL));

        if (!isFormValid) {
            request.setAttribute(RequestAttribute.PIERCER_TO_EDIT, piercerToUpdate);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, INVALID_INPUT);
            return new Router(PagePath.EDIT_PIERCER_WORKING_INFO, Router.RouterType.FORWARD);
        }

        try {
            piercerService.updateWorkingInfo(piercerToUpdate);
            return new Router(PagePath.GO_TO_UPDATED_USERS_LIST, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to execute EditPiercerWorkingInfoCommand", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
