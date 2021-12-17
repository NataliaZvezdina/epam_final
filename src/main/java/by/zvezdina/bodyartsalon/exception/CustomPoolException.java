package by.zvezdina.bodyartsalon.exception;

public class CustomPoolException extends Exception {

    public CustomPoolException() {
    }

    public CustomPoolException(String message) {
        super(message);
    }

    public CustomPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomPoolException(Throwable cause) {
        super(cause);
    }
}
