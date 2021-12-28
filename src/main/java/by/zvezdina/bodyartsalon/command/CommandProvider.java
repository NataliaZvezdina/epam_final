package by.zvezdina.bodyartsalon.command;

import by.zvezdina.bodyartsalon.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static CommandProvider instance;
    private final Map<CommandType, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.LOGOUT, new LogOutCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.VERIFY, new VerifyCommand());
        commands.put(CommandType.SHOW_ALL_JEWELRY, new ShowAllJewelryCommand());

        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GO_TO_LOGIN, new GoToLoginPageCommand());
        commands.put(CommandType.LOGIN_USER, new LoginUserCommand());
        commands.put(CommandType.GO_TO_PROFILE, new GoToProfilePageCommand());
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
