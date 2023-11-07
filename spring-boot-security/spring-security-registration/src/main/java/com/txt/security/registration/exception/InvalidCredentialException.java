package com.txt.security.registration.exception;


public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException(String message) {
        super(message);
    }
}