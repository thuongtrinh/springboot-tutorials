package com.txt.security.registration.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.txt.security.registration.dto.common.MessageCode;
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
    private MessageCode status;

}
