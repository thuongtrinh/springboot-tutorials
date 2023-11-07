package com.txt.security.registration.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class APIPaginationRequestDTO implements Serializable {
    private int pageIndex;
    private int itemsPerPage;
    private List<String> orders = new ArrayList<>();
    private String sort;

    public APIPaginationRequestDTO() {
        this.pageIndex = 0;
        this.itemsPerPage = 10;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
