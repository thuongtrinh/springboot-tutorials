package com.txt.security.registration.exception;

import com.txt.security.registration.util.CommonUtils;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Object... listParams) {
        super(CommonUtils.generateMessage(message, listParams));
    }

}
