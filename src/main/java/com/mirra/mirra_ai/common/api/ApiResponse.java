package com.mirra.mirra_ai.common.api;
import java.time.Instant;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        ApiError error,
        Instant timestamp,
        String traceId
        ) {
            
}
