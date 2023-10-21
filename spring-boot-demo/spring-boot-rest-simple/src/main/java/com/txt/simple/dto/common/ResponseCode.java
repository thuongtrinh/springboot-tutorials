package com.txt.simple.dto.common;

import org.apache.commons.lang3.StringUtils;

public enum ResponseCode {

    FAILED("500", "FAILED"),
    SUCCESSFUL("200", "SUCCESS"),
    API_FAILED("444", "Call API failed"),
    E001("001", "Request parameter invalid");

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
