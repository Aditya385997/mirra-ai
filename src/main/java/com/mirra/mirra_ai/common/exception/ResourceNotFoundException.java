package com.mirra.mirra_ai.common.exception;

import org.springframework.http.HttpStatus;

import com.mirra.mirra_ai.common.enums.ErrorCode;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message, ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
