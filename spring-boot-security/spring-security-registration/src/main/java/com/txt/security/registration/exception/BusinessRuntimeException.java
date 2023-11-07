package com.txt.security.registration.exception;

import com.txt.security.registration.util.CommonUtils;
import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {
    private String code;
    private String businessKey;

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(String message, Object... listParams) {
        super(CommonUtils.generateMessage(message, listParams));
    }

    public BusinessRuntimeException(String message, String businessKey) {
        super(message);
        this.businessKey = businessKey;
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(String code, String message, String businessKey) {
        super(message);
        this.code = code;
        this.businessKey = businessKey;
    }

}
