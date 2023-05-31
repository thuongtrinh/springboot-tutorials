package com.txt.mongoredis.dto.track;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackBookRequest {
    private List<String> ids;
    private String author;
    private String data;
}
