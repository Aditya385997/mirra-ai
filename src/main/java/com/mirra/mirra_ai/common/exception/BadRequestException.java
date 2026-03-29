package com.mirra.mirra_ai.common.exception;

import org.springframework.http.HttpStatus;

import com.mirra.mirra_ai.common.enums.ErrorCode;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }
}
