package minute.system.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import minute.system.back.model.dto.ApiResponseDTO;
import minute.system.back.model.dto.error.ApiError;
import minute.system.back.model.dto.error.ApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(Exception ex) {
        ApiError error = new ApiError("Bad Request", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO<>(null, List.of(error)));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleGenericException(ApiException ex) {
        ApiError error = new ApiError("Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO<>(null, List.of(error)));
    }

}

