package by.zvezdina.bodyartsalon.controller.command.impl.client;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.exception.ServiceException;
import by.zvezdina.bodyartsalon.model.entity.Client;
import by.zvezdina.bodyartsalon.model.service.ClientService;
import by.zvezdina.bodyartsalon.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE_FOR_INVALID_INPUT_DATA = "invalid.amount.input";
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long clientId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        String inputMoney = request.getParameter(RequestParameter.MONEY_TO_ADD);

        BigDecimal clientBalance = (BigDecimal) session.getAttribute(SessionAttribute.USER_MONEY);
        boolean isValid = clientService.validateMoneyToAdd(inputMoney, clientBalance);

        if (!isValid) {
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, MESSAGE_FOR_INVALID_INPUT_DATA);
            return new Router(PagePath.TOP_UP_BALANCE, Router.RouterType.FORWARD);
        }

        BigDecimal moneyToAdd = new BigDecimal(inputMoney);

        try {
            boolean updated = clientService.updateBalance(clientId, moneyToAdd);
            if(!updated) {
                return new Router(PagePath.TOP_UP_BALANCE, Router.RouterType.FORWARD);
            }
            Client currentClient = clientService.findById(clientId);
            session.setAttribute(SessionAttribute.USER_MONEY, currentClient.getMoney());
            return new Router(PagePath.BALANCE_RECHARGED, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while recharging balance: ", e);
            return new Router(PagePath.ERROR_500_PAGE, Router.RouterType.FORWARD);
        }
    }
}
