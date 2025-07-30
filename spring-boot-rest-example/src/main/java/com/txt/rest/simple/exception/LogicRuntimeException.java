package com.txt.rest.simple.exception;

public class LogicRuntimeException extends RuntimeException {

    public LogicRuntimeException(String message) {
        super(message);
    }

    public LogicRuntimeException(String message, Exception e) {
        super(message, e);
    }

}
