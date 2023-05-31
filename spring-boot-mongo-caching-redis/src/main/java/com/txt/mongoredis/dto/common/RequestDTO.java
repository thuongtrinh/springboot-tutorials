package com.txt.mongoredis.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = 5189821266152031268L;

    @Schema(
            name = "exchangeId",
            description = "UUID characters"
    )
    @Size(max = 255, message = "255 characters")
    @JsonProperty("exchangeId")
    private String exchangeId;

    @Schema(
            name = "correlationId",
            description = "correlation id"
    )
    @Size(max = 255, message = "255 characters")
    @JsonProperty("correlationId")
    private String correlationId = null;

    @Schema(
            name = "createdBy",
            description = "User who called API"
    )
    @Size(max = 20, message = "20 characters")
    @JsonProperty("createdBy")
    private String createdBy;

    @Schema(
            name = "createdDate",
            description = "Created date"
    )
    @JsonProperty("createdDate")
    private String createdDate;

    @Schema(
            name = "source",
            description = "source"
    )
    @JsonProperty("source")
    private String source = null;

}
