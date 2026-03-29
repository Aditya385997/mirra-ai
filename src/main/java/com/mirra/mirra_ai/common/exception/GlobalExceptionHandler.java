package com.mirra.mirra_ai.common.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mirra.mirra_ai.common.api.ApiError;
import com.mirra.mirra_ai.common.api.ApiResponse;
import com.mirra.mirra_ai.common.api.ErrorResponse;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException ex, HttpServletRequest request) {
        log.warn("Handled business exception: path={}, code={}, message={}",
                request.getRequestURI(), ex.getErrorCode().name(), ex.getMessage());

        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.of(ex.getMessage(), ex.getErrorCode().name()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {
        List<ApiError.FieldErrorDetail> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldError)
                .toList();

        log.warn("Validation failed: path={}, traceId={}", request.getRequestURI(), MDC.get("traceId"));

        return ResponseEntity.badRequest()
                .body(ErrorResponse.of("Validation failed", "VALIDATION_ERROR", details));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnhandled(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception: path={}, traceId={}", request.getRequestURI(), MDC.get("traceId"), ex);

        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of("Something went wrong", "INTERNAL_SERVER_ERROR"));
    }

    private ApiError.FieldErrorDetail mapFieldError(FieldError fieldError) {
        return new ApiError.FieldErrorDetail(fieldError.getField(), fieldError.getDefaultMessage());
    }
}