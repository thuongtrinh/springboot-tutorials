package com.txt.rest.simple.dto.common;

import com.txt.rest.simple.constant.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Schema(
        name = "APIStandardResponseDTO",
        description = "Base response"
)
@AllArgsConstructor
@ToString
@Data
@Builder
public class APIStandardResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status = Constants.SUCCESS;
    private String exchangeId;
    private T data;
    private ResponseStatus responseStatus;

    public APIStandardResponseDTO() {
        this.setResponseStatus(null);
    }
}
