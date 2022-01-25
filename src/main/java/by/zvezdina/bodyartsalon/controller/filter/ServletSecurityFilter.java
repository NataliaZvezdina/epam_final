package by.zvezdina.bodyartsalon.controller.filter;

import by.zvezdina.bodyartsalon.controller.command.*;
import by.zvezdina.bodyartsalon.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;

import static by.zvezdina.bodyartsalon.controller.command.CommandType.*;

@WebFilter(urlPatterns = {"/controller"})
public class ServletSecurityFilter implements Filter {
    private final EnumMap<Role, EnumSet<CommandType>> roleMap =
            new EnumMap<>(Role.class);
    private final EnumSet<CommandType> guestCommands = EnumSet.of(
            CHANGE_LOCALE,
            SIGN_IN,
            LOGOUT,
            SIGN_UP,
            VERIFY,
            SHOW_ALL_JEWELRY,
            SHOW_ALL_FACILITIES,
            SHOW_ALL_ACTIVE_PIERCERS);

    private final EnumSet<CommandType> adminCommands = EnumSet.of(
            CHANGE_LOCALE,
            LOGOUT,
            SHOW_ALL_JEWELRY,
            SHOW_ALL_FACILITIES,
            ADD_JEWELRY,
            EDIT_JEWELRY,
            GO_TO_EDIT_JEWELRY,
            DELETE_JEWELRY,
            RESTORE_JEWELRY,
            DELETE_FACILITY,
            RESTORE_FACILITY,
            GO_TO_EDIT_FACILITY,
            EDIT_FACILITY,
            ADD_FACILITY,
            SHOW_ALL_ORDERS,
            OPEN_ORDER,
            CANCEL_ORDER,
            MARK_ORDER_AS_RECEIVED,
            UPDATE_PASSWORD,
            UPDATE_PROFILE,
            GO_TO_UPDATE_PROFILE,
            SHOW_ALL_USERS,
            DELETE_USER,
            RESTORE_USER,
            OPEN_PROFILE,
            CHANGE_CLIENT_DISCOUNT,
            SHOW_ALL_ACTIVE_PIERCERS,
            SHOW_ALL_APPOINTMENTS_BY_ADMIN,
            OPEN_SINGLE_APPOINTMENT,
            ADD_ADMIN,
            ADD_PIERCER,
            GO_TO_EDIT_PIERCER_WORKING_INFO,
            EDIT_PIERCER_WORKING_INFO,
            NOT_FOUND_PAGE);

    private final EnumSet<CommandType> clientCommands = EnumSet.of(
            CHANGE_LOCALE,
            LOGOUT,
            SHOW_ALL_JEWELRY,
            SHOW_ALL_FACILITIES,
            ADD_ITEM_TO_BASKET,
            SHOW_BASKET,
            CREATE_ORDER,
            RECOUNT_ORDER_WHILE_ADDING_ITEM,
            RECOUNT_ORDER_WHILE_REMOVING_ITEM,
            SHOW_ORDERS_BY_CLIENT,
            OPEN_ORDER,
            CANCEL_ORDER,
            UPDATE_PASSWORD,
            UPDATE_PROFILE,
            GO_TO_UPDATE_PROFILE,
            TOP_UP_BALANCE,
            GO_TO_MAKE_APPOINTMENT,
            MAKE_APPOINTMENT,
            OPEN_SINGLE_APPOINTMENT,
            SHOW_ALL_RELEVANT_APPOINTMENTS_BY_CLIENT,
            CANCEL_APPOINTMENT,
            SHOW_ALL_ACTIVE_PIERCERS,
            NOT_FOUND_PAGE);

    private final EnumSet<CommandType> piercerCommands = EnumSet.of(
            CHANGE_LOCALE,
            LOGOUT,
            SHOW_ALL_JEWELRY,
            SHOW_ALL_FACILITIES,
            SHOW_ALL_ACTIVE_PIERCERS,
            SHOW_ALL_RELEVANT_APPOINTMENTS_BY_PIERCER,
            SHOW_APPOINTMENTS_FOR_TODAY,
            OPEN_SINGLE_APPOINTMENT,
            UPDATE_PASSWORD,
            UPDATE_PROFILE,
            GO_TO_UPDATE_PROFILE);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleMap.put(Role.GUEST, guestCommands);
        roleMap.put(Role.ADMIN, adminCommands);
        roleMap.put(Role.CLIENT, clientCommands);
        roleMap.put(Role.PIERCER, piercerCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Role userRole = (Role) session.getAttribute(SessionAttribute.USER_ROLE);
        if (userRole == null) {
            userRole = Role.GUEST;
        }

        String commandParameter = request.getParameter(RequestParameter.COMMAND);
        CommandType command = CommandType.valueOf(commandParameter.toUpperCase());

        boolean hasAccess = roleMap.get(userRole)
                .stream()
                .anyMatch(c -> c == command);

        if (!hasAccess) {
            response.sendRedirect(request.getContextPath() + "/" + PagePath.HOME);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
