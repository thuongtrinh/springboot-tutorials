package com.txt.mongoredis.dto.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private List<Error> errors;

    public ResponseStatus code(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseStatus message(String message) {
        this.message = message;
        return this;
    }

    public ResponseStatus errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseStatus addErrorsItem(Error errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseStatus responseStatus = (ResponseStatus) o;
        return Objects.equals(this.code, responseStatus.code) &&
                Objects.equals(this.message, responseStatus.message) &&
                Objects.equals(this.errors, responseStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseStatus {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
