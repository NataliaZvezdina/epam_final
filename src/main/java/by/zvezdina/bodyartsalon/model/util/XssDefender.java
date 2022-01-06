package by.zvezdina.bodyartsalon.model.util;

public class XssDefender {

    public static final String OPEN_TAG = "<";
    public static final String CLOSE_TAG = ">";
    public static final String REPLACEMENT = "";
    public static XssDefender instance;

    private XssDefender() {
    }

    public static XssDefender getInstance() {
        if (instance == null) {
            instance = new XssDefender();
        }
        return instance;
    }

    public String safeFormData(String data) {
        return !data.isBlank() ?
                data.strip().replace(OPEN_TAG, REPLACEMENT).replace(CLOSE_TAG, REPLACEMENT) : data;
    }
}
