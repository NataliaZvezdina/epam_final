package by.zvezdina.bodyartsalon.util;

public class FormValidator {

    private static final String CHECK_LOGIN_REGEX = "[A-Za-z0-9]{3,20}";

    private static final String CHECK_PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";

    private static final String CHECK_FIRST_AND_LAST_NAME = "^[A-ZА-Я]{1}[a-zа-я]{2,20}$";

    private static final String CHECK_EMAIL_REGEX =
            "^(?=.{1,45}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String CHECK_IMAGE_URL = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";

    private static final String CHECK_MONEY_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";

    private static final String LIMIT_FOR_RECHARGED_MONEY_REGEX = "^[0-9]{1,2}(\\.[0-9]{1,2})?$";

    private static final int MAX_STRING_LENGTH = 90;

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
        return login != null && login.matches(CHECK_LOGIN_REGEX);
    }

    public boolean checkPassword(String password) {
        return password != null && password.matches(CHECK_PASSWORD_REGEX);
    }

    public boolean checkFirstName(String firstName) {
        return firstName != null && firstName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkLastName(String lastName) {
        return lastName != null && lastName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkEmail(String email) {
        return email != null && email.matches(CHECK_EMAIL_REGEX);
    }

    public boolean checkMoney(String money) {
        return money != null && money.matches(CHECK_MONEY_REGEX);
    }

    public boolean checkRechargedMoney(String money) {
        return money != null && money.matches(LIMIT_FOR_RECHARGED_MONEY_REGEX);
    }

    public boolean checkImageUrl(String imageUrl) {
        return imageUrl != null && imageUrl.matches(CHECK_IMAGE_URL);
    }
}
