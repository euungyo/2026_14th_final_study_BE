package com.example.demo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

        USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
        INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
        DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
        UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
        FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");

        private final HttpStatus status;
        private final String message;
    }
