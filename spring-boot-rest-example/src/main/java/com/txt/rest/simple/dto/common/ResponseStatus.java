package com.txt.rest.simple.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private List<ErrorDTO> errorDTOs;
}
