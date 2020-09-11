package com.azerion.sso.exception;

public class InValidJwtTokenException extends RuntimeException {
    public InValidJwtTokenException(String message) {
        super(message);
    }
}
