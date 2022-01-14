package by.zvezdina.bodyartsalon.controller.command;

import by.zvezdina.bodyartsalon.controller.command.impl.*;
import by.zvezdina.bodyartsalon.controller.command.impl.admin.*;
import by.zvezdina.bodyartsalon.controller.command.impl.client.*;
import by.zvezdina.bodyartsalon.controller.command.impl.common.*;
import by.zvezdina.bodyartsalon.controller.command.impl.piercer.ShowAllRelevantAppointmentsByPiercerCommand;
import by.zvezdina.bodyartsalon.controller.command.impl.piercer.ShowAppointmentsForTodayCommand;

import java.util.EnumMap;

public class CommandProvider {

    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    private CommandProvider() {
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.LOGOUT, new LogOutCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.VERIFY, new VerifyCommand());
        commands.put(CommandType.SHOW_ALL_JEWELRY, new ShowAllJewelryCommand());
        commands.put(CommandType.ADD_JEWELRY, new AddJewelryCommand());
        commands.put(CommandType.DELETE_JEWELRY, new DeleteJewelryCommand());
        commands.put(CommandType.RESTORE_JEWELRY, new RestoreJewelryCommand());
        commands.put(CommandType.EDIT_JEWELRY, new EditJewelryCommand());
        commands.put(CommandType.GO_TO_EDIT_JEWELRY, new GoToEditJewelryCommand());

        commands.put(CommandType.SHOW_ALL_FACILITIES, new ShowAllFacilitiesCommand());
        commands.put(CommandType.DELETE_FACILITY, new DeleteFacilityCommand());
        commands.put(CommandType.RESTORE_FACILITY, new RestoreFacilityCommand());
        commands.put(CommandType.GO_TO_EDIT_FACILITY, new GoToEditFacilityCommand());
        commands.put(CommandType.EDIT_FACILITY, new EditFacilityCommand());
        commands.put(CommandType.ADD_FACILITY, new AddFacilityCommand());

        commands.put(CommandType.SHOW_ALL_ORDERS, new ShowAllOrdersCommand());
        commands.put(CommandType.OPEN_ORDER, new OpenOrderCommand());
        commands.put(CommandType.ADD_ITEM_TO_BASKET, new AddItemToBasketCommand());
        commands.put(CommandType.SHOW_BASKET, new ShowBasketCommand());
        commands.put(CommandType.CREATE_ORDER, new CreateOrderCommand());
        commands.put(CommandType.RECOUNT_ORDER_WHILE_ADDING_ITEM, new RecountOrderWhileAddingItemCommand());
        commands.put(CommandType.RECOUNT_ORDER_WHILE_REMOVING_ITEM, new RecountOrderWhileRemovingItemCommand());

        commands.put(CommandType.UPDATE_PASSWORD, new UpdatePasswordCommand());
        commands.put(CommandType.UPDATE_PROFILE, new UpdateProfileCommand());
        commands.put(CommandType.GO_TO_UPDATE_PROFILE, new GoToUpdateProfileCommand());
        commands.put(CommandType.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(CommandType.DELETE_USER, new DeleteUserCommand());
        commands.put(CommandType.RESTORE_USER, new RestoreUserCommand());
        commands.put(CommandType.OPEN_PROFILE, new OpenProfileCommand());
        commands.put(CommandType.CHANGE_CLIENT_DISCOUNT, new ChangeClientDiscountCommand());

        commands.put(CommandType.TOP_UP_BALANCE, new TopUpBalanceCommand());

        commands.put(CommandType.GO_TO_MAKE_APPOINTMENT, new GoToMakeAppointmentCommand());
        commands.put(CommandType.MAKE_APPOINTMENT, new MakeAppointmentCommand());
        commands.put(CommandType.SHOW_ALL_RELEVANT_APPOINTMENTS_BY_PIERCER,
                new ShowAllRelevantAppointmentsByPiercerCommand());
        commands.put(CommandType.SHOW_APPOINTMENTS_FOR_TODAY, new ShowAppointmentsForTodayCommand());
        commands.put(CommandType.SHOW_ALL_RELEVANT_APPOINTMENTS_BY_CLIENT,
                new ShowAllRelevantAppointmentsByClientCommand());
        commands.put(CommandType.CANCEL_APPOINTMENT, new CancelAppointmentCommand());
        commands.put(CommandType.OPEN_SINGLE_APPOINTMENT, new OpenSingleAppointmentCommand());
        commands.put(CommandType.SHOW_ALL_ACTIVE_PIERCERS, new ShowAllActivePiercersCommand());
        commands.put(CommandType.ADD_ADMIN, new AddAdminCommand());

        commands.put(CommandType.DEFAULT, new DefaultCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }

        return commands.get(commandType);
    }
}
