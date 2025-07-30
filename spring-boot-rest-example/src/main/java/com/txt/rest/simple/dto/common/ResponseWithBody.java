package com.txt.rest.simple.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "responseWithBody",
        description = "Base response"
)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@ToString
@Data
public class ResponseWithBody<T> extends ObjResult {

    private static final long serialVersionUID = 1L;

    @Schema(
            name = "body",
            description = "Body payload"
    )
    private T body;

    ResponseStatus responseStatus;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus status) {
        this.responseStatus = status;
    }

    public ResponseWithBody() {
        this.setResponseStatus(null);
    }
}
