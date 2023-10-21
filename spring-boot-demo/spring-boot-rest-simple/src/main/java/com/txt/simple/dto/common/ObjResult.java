package com.txt.simple.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ObjResult extends RequestDTO {

    private static final long serialVersionUID = 1L;
    private String status = "SUCCESS";

    @JsonProperty(value = "oErrorResult")
    private ObjError oErrorResult;

}
