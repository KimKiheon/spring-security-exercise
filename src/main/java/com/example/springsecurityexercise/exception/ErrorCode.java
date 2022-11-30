package com.example.springsecurityexercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND,"UserName을 찾을 수 없습니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"");

    private HttpStatus status;
    private String message;
}
