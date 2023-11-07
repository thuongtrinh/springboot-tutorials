package com.txt.security.registration.exception;

import com.txt.security.registration.util.CommonUtils;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object... listParams) {
        super(CommonUtils.generateMessage(message, listParams));
    }
}
