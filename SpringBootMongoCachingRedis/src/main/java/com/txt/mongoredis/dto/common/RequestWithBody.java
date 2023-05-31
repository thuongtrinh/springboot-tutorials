package com.txt.mongoredis.dto.common;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestWithBody<T> extends RequestDTO {
    /**
     *
     */
    private static final long serialVersionUID = 4232405318892240916L;
    private T body;

}
