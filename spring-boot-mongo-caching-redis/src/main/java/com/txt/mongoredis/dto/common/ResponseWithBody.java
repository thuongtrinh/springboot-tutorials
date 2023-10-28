package com.txt.mongoredis.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@ToString
@Data
public class ResponseWithBody<T> extends ObjResult {

    private static final long serialVersionUID = 1L;

    private T body;
    ResponseStatus status;

}
