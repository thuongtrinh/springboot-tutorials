package com.txt.rest.simple.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;

    private String message;

    private List<Error> errors;

    public ResponseStatus code(Integer code) {
        this.code = code;
        return this;
    }

}
