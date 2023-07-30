package minute.system.back.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.error.ApiError;
import minute.system.back.model.dto.error.exception.ApiBackEndException;
import minute.system.back.model.dto.error.exception.ApiBadRequestException;
import minute.system.back.model.dto.error.exception.ApiNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INTERNAL_ERROR = "Internal Error";
    private static final String BAD_REQUEST = "Bad Request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(MethodArgumentNotValidException ex) {
        List<ApiError> errors = new ArrayList<>();
        for (ObjectError errorEx : ex.getBindingResult().getAllErrors()) {
            errors.add(new ApiError(errorEx.getObjectName(), errorEx.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO<>(null, errors));
    }

    @ExceptionHandler(ApiBadRequestException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(ApiBadRequestException ex) {
        ApiError error = new ApiError(BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO<>(null, List.of(error)));
    }

    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(ApiNotFoundException ex) {
        ApiError error = new ApiError(BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO<>(null, List.of(error)));
    }

    @ExceptionHandler(ApiBackEndException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(ApiBackEndException ex) {
        ApiError error = new ApiError(INTERNAL_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO<>(null, List.of(error)));
    }

}

