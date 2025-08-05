package com.txt.rest.simple.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errorField;
    private String errorMessage;
}
