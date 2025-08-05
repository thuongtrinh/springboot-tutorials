package com.txt.rest.simple.dto.common;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class PaginationResponse<T> {
    protected int pageIndex;
    protected int sizePage;
    protected int totalRecords;
    protected List<String> orders = new ArrayList<>();
    protected String sort;

    public PaginationResponse(Collection<T> records) {
        this.totalRecords = records.size();
    }

    public PaginationResponse(Collection<T> records, int pageIndex, int sizePage) {
        this.totalRecords = records.size();
        this.pageIndex = pageIndex;
        this.sizePage = sizePage;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
