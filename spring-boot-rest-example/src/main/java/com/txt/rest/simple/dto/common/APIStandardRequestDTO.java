package com.txt.rest.simple.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "APIStandardRequestDTO",
        description = "Base request"
)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIStandardRequestDTO<T> extends RequestDTO {
    private static final long serialVersionUID = 1L;
    private T data;
}
