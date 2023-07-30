package minute.system.back.model.dto.error.exception;

public class ApiBackEndException extends RuntimeException{
    public ApiBackEndException() {
        super();
    }

    public ApiBackEndException(String message) {
        super(message);
    }

    public ApiBackEndException(String message, Throwable cause) {
        super(message, cause);
    }
}
