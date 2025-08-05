package com.txt.rest.simple.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collection;
import java.util.List;

@Schema(
        name = "APIPagingResponseDTO",
        description = "Base paging response"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIPagingResponseDTO<T> {
    private static final long serialVersionUID = 1L;

    private PaginationResponse<T> paging;
    private List<T> records;

    public APIPagingResponseDTO(Collection<T> records) {
        this.paging = new PaginationResponse(records);
        this.records = records.stream().toList();
    }

    public APIPagingResponseDTO(Collection<T> records, int page, int size) {
        this.paging = new PaginationResponse(records, page, size);
        this.records = records.stream().toList();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
