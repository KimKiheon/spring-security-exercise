package com.example.springsecurityexercise.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    @Override
    public String toString() {
        if (message == null) return errorCode.getMessage();
        return String.format("%s %s", errorCode.getMessage(), message);
    }
}
