package com.txt.rest.simple.dto.common;

import org.apache.commons.lang3.StringUtils;

public enum ResponseCode {

    SUCCESSFUL("200", "SUCCESS"),
    FAILED("500", "FAILED"),
    ERROR_001("400", "Request parameter invalid"),
    ERROR_002("403 ", "Request Forbidden"),
    ERROR_003("404  ", "Request Not Found");

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
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
