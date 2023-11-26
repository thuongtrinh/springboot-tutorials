package com.txt.security.registration.dto.common;

import org.apache.commons.lang3.StringUtils;

public enum ResponseCode {

    FAILED("500", "FAILED"),
    SUCCESSFUL("200", "SUCCESS"),

    MS001("MS001", "Token expired");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseCode fromValue(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ResponseCode e : ResponseCode.values()) {
            if (code.equalsIgnoreCase(e.code)) {
                return e;
            }
        }
        return null;
    }
}
