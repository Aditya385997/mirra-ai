package com.mirra.mirra_ai.common.api;

import java.time.Instant;

import org.slf4j.MDC;

public final class SuccessResponse {

    private SuccessResponse() {
    }

    public static <T> ApiResponse<T> of(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                null,
                Instant.now(),
                MDC.get("traceId")
        );
    }

    public static ApiResponse<Void> of(String message) {
        return of(message, null);
    }
}