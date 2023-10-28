package com.txt.mongoredis.dto.common;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestWithBody<T> extends RequestDTO {
    private static final long serialVersionUID = 1L;

    private T body;

}
