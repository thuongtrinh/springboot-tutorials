package com.txt.simple.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String type;
    private String message;

    public Error code(String code) {
        this.code = code;
        return this;
    }

}
