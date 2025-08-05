package com.txt.rest.simple.dto.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationRequest extends RequestDTO {
    protected int pageIndex;
    protected int sizePage;
    protected List<String> orders = new ArrayList<>();
    protected String sort;
}
