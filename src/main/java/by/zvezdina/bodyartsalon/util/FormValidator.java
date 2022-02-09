package by.zvezdina.bodyartsalon.util;

/**
 * The type Form validator.
 */
public class FormValidator {

    private static final String CHECK_LOGIN_REGEX = "[A-Za-z0-9]{3,20}";

    private static final String CHECK_PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";

    private static final String CHECK_FIRST_AND_LAST_NAME = "^[A-ZА-Я]{1}[a-zа-я]{2,20}$";

    private static final String CHECK_EMAIL_REGEX =
            "^(?=.{1,45}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String CHECK_IMAGE_URL = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";

    private static final String CHECK_MONEY_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";

    private static final String LIMIT_FOR_RECHARGED_MONEY_REGEX = "^[0-9]{1,2}(\\.[0-9]{1,2})?$";

    private static final String LIMIT_FOR_VALID_APPOINTMENT_HOURS = "(1[0-9])|20";

    private static final int MAX_STRING_LENGTH = 90;

    private static FormValidator instance;

    private FormValidator() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new FormValidator();
        }
        return instance;
    }

    /**
     * Check login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean checkLogin(String login) {
        return login != null && login.matches(CHECK_LOGIN_REGEX);
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public boolean checkPassword(String password) {
        return password != null && password.matches(CHECK_PASSWORD_REGEX);
    }

    /**
     * Check first name boolean.
     *
     * @param firstName the first name
     * @return the boolean
     */
    public boolean checkFirstName(String firstName) {
        return firstName != null && firstName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    /**
     * Check last name boolean.
     *
     * @param lastName the last name
     * @return the boolean
     */
    public boolean checkLastName(String lastName) {
        return lastName != null && lastName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    /**
     * Check email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkEmail(String email) {
        return email != null && email.matches(CHECK_EMAIL_REGEX);
    }

    /**
     * Check money boolean.
     *
     * @param money the money
     * @return the boolean
     */
    public boolean checkMoney(String money) {
        return money != null && money.matches(CHECK_MONEY_REGEX);
    }

    /**
     * Check recharged money boolean.
     *
     * @param money the money
     * @return the boolean
     */
    public boolean checkRechargedMoney(String money) {
        return money != null && money.matches(LIMIT_FOR_RECHARGED_MONEY_REGEX);
    }

    /**
     * Check image url boolean.
     *
     * @param imageUrl the image url
     * @return the boolean
     */
    public boolean checkImageUrl(String imageUrl) {
        return imageUrl != null && imageUrl.matches(CHECK_IMAGE_URL);
    }

    /**
     * Check on max length boolean.
     *
     * @param field the field
     * @return the boolean
     */
    public boolean checkOnMaxLength(String field) {
        return field != null && field.length() <= MAX_STRING_LENGTH;
    }

    /**
     * Check hour boolean.
     *
     * @param hour the hour
     * @return the boolean
     */
    public boolean checkHour(String hour) {
        return hour != null && hour.matches(LIMIT_FOR_VALID_APPOINTMENT_HOURS);
    }
}
