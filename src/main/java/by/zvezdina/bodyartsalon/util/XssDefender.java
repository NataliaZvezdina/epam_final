package by.zvezdina.bodyartsalon.util;

/**
 * The type Xss defender.
 */
public class XssDefender {

    /**
     * The constant OPEN_TAG.
     */
    public static final String OPEN_TAG = "<";
    /**
     * The constant CLOSE_TAG.
     */
    public static final String CLOSE_TAG = ">";
    /**
     * The constant REPLACEMENT.
     */
    public static final String REPLACEMENT = "";
    /**
     * The constant instance.
     */
    public static XssDefender instance;

    private XssDefender() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static XssDefender getInstance() {
        if (instance == null) {
            instance = new XssDefender();
        }
        return instance;
    }

    /**
     * Safe form data string.
     *
     * @param data the data
     * @return the string
     */
    public String safeFormData(String data) {
        return !data.isBlank() ?
                data.strip().replace(OPEN_TAG, REPLACEMENT).replace(CLOSE_TAG, REPLACEMENT) : data;
    }
}
