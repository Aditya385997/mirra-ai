package com.mirra.mirra_ai.common.exception;

import org.springframework.http.HttpStatus;

import com.mirra.mirra_ai.common.enums.ErrorCode;

public class InternalServerException extends BaseException{
    public InternalServerException(String message) {
         super(message, ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
