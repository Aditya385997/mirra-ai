package com.mirra.mirra_ai.common.exception;

import org.springframework.http.HttpStatus;

import com.mirra.mirra_ai.common.enums.ErrorCode;

public class ConflictException extends BaseException {

    public ConflictException(String message) {
        super(message, ErrorCode.CONFLICT, HttpStatus.CONFLICT);
    }
}
