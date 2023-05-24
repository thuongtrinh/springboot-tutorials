package com.txt.mongoredis.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultDTO<T> {

    private static final long serialVersionUID = 1L;

    private T body;
    private ResponseStatus status;

}
