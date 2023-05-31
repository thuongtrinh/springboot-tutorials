package com.txt.mongoredis.dto.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ObjErrorList {

    private String errorCode;
    private String errorDescription;
    private String errorFieldPrefix;
    private String errorField;

    public ObjErrorList() {
        this.errorFieldPrefix = "";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorFieldPrefix() {
        return errorFieldPrefix;
    }

    public void setErrorFieldPrefix(String errorFieldPrefix) {
        this.errorFieldPrefix = errorFieldPrefix;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
