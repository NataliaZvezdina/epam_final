package by.zvezdina.bodyartsalon.model.util;

public class FormValidator {

    private static final String CHECK_LOGIN_REGEX = "^[\\w_]{3,20}$";
    private static final String CHECK_PASSWORD_REGEX = "^[\\w_]{8,20}$";
    private static final String CHECK_FIRST_AND_LAST_NAME = "^[\\p{IsAlphabetic}\\-]{2,20}$";
    private static final String CHECK_EMAIL_REGEX = "^([\\w_\\.-]+)@([\\w_\\.-]+)\\.([a-z\\.]{2,6})$";

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

    public boolean checkFirstName(String firstName) {
        return firstName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkLastName(String lastName) {
        return lastName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkEmail(String email) {
        return email.matches(CHECK_EMAIL_REGEX);
    }
}
