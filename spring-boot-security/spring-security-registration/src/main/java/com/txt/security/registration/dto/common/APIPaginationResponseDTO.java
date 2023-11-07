package com.txt.security.registration.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class APIPaginationResponseDTO<T> {
    private List<T> records;
    private Paging paging;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Paging {
        private int index;
        private int size;
        private long total;
        private int pages;
        private List<String> orders = new ArrayList<>();
        private String sort = "ASC";

        public Paging(Collection<?> records) {
            this.index = 0;
            this.size = records.size();
            this.total = records.size();
            this.pages = 1;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public APIPaginationResponseDTO(Collection<T> records) {
        this.records = records.stream().toList();
        this.paging = new Paging(records);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
