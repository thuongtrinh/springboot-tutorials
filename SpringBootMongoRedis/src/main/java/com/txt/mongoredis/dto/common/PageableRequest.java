package com.txt.mongoredis.dto.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageableRequest {

    private Integer start = 0;
    private Integer size = 10;
    private List<String> orders = new ArrayList<>();
    private String sort = "ASC";
}
