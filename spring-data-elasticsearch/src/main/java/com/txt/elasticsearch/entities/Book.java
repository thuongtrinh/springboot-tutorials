package com.txt.elasticsearch.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "books")
public class Book {

    @Id
    @Field
    private String id;

    @Field
    private String title;

    @Field
    private int publicationYear;

    @Field
    private String authorName;

    @Field
    private String isbn;
}
