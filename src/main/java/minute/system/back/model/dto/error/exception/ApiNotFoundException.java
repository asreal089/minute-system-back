package minute.system.back.model.dto.error.exception;

public class ApiNotFoundException extends RuntimeException {
    public ApiNotFoundException() {
        super();
    }

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
