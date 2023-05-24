package com.txt.mongoredis.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
        name = "responseWithBody",
        description = "Base response"
)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@ToString
public class ResponseWithBody<T> extends ObjResult {

    /**
     *
     */
    private static final long serialVersionUID = 7346808926749564665L;

    @Schema(
            name = "body",
            description = "Body payload"
    )
    private T body;

    ResponseStatus status;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public ResponseStatus getResponseStatus() {
        return status;
    }

    public void setResponseStatus(ResponseStatus status) {
        this.status = status;
    }

    public ResponseWithBody() {
        this.setStatus(null);
    }
}
