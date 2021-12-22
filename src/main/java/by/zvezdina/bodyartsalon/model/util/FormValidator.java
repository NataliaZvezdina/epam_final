package by.zvezdina.bodyartsalon.model.util;

public class FormValidator {

    private static final String CHECK_LOGIN_REGEX = "^[\\w_]{3,20}$";

    private static final String CHECK_PASSWORD_REGEX = "^[\\w_]{7,20}$";

    private static FormValidator instance;

    private FormValidator() {
    }

    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new FormValidator();
        }
        return instance;
    }

    public boolean checkLogin(String login) {
        return login.matches(CHECK_LOGIN_REGEX);
    }

    public boolean checkPassword(String password) {
        return password.matches(CHECK_PASSWORD_REGEX);
    }
}
