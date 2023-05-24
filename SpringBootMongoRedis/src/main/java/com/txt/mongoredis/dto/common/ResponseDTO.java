package com.txt.mongoredis.dto.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDTO<T> {

    private long total;
    private int start;
    private int size;
    private List<String> orders = new ArrayList<>();
    private String sort;
    private List<T> data = new ArrayList<>();
}
