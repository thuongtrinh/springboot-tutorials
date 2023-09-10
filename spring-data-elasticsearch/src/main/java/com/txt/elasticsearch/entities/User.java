package com.txt.elasticsearch.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(indexName = "user_index")
public class User {

    @Id
    @Field
    private String userId;

    @Field
    private String name;

    @Field
    private Date creationDate = new Date();

    @Field
    private Map<String, String> userSettings = new HashMap<>();

}
