package minute.system.back.model.dto.error.exception;

public class ApiBadRequestException extends RuntimeException {

    public ApiBadRequestException() {
        super();
    }

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}