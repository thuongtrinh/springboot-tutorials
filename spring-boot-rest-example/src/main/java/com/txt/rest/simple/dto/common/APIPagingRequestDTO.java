package com.txt.rest.simple.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Schema(
        name = "APIPagingRequestDTO",
        description = "Base paging request"
)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ToString
public class APIPagingRequestDTO<T> extends PaginationRequest {
    private static final long serialVersionUID = 1L;
    private T data;

    public APIPagingRequestDTO() {
        this.pageIndex = 0;
        this.sizePage = 10;
        this.sort = "ASC";
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
