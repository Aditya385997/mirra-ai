package com.mirra.mirra_ai.common.api;

import java.time.Instant;
import java.util.List;

import org.slf4j.MDC;

public final class ErrorResponse {

    private ErrorResponse() {
    }

    public static ApiResponse<Void> of(String message, String code) {
        return new ApiResponse<>(
                false,
                message,
                null,
                new ApiError(code, List.of()),
                Instant.now(),
                MDC.get("traceId")
        );
    }

    public static ApiResponse<Void> of(String message, String code, List<ApiError.FieldErrorDetail> details) {
        return new ApiResponse<>(
                false,
                message,
                null,
                new ApiError(code, details),
                Instant.now(),
                MDC.get("traceId")
        );
    }
}