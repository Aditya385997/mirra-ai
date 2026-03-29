package com.mirra.mirra_ai.common.api;

import java.util.List;

public record ApiError(
        String code,
        List<FieldErrorDetail> details
) {
    public record FieldErrorDetail(
            String field,
            String message
    ) {}
}