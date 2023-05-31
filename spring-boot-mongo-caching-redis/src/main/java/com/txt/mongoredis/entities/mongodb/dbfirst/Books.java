package com.txt.mongoredis.entities.mongodb.dbfirst;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "books")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Books implements Serializable {
    @Id
    private String id;
    @Field
    private String title;
    @Field
    private String isbn;
    @Field
    private Integer pageCount;
    @Field
    private Date publishedDate;
    @Field
    private String thumbnailUrl;
    @Field
    private String shortDescription;
    @Field
    private String longDescription;
    @Field
    private String status;
    @Field
    private List<String> authors;
    @Field
    private List<String> categories;
}
