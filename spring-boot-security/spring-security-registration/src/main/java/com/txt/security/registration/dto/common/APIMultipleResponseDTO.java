package com.txt.security.registration.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class APIMultipleResponseDTO<T> {
    private List<T> responseStatus;
    private String code;
    private String message;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
