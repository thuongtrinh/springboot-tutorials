package com.txt.elasticsearch.entities;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

//@Document(indexName = "blog", type = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = Text, fielddata = true), otherFields = {@InnerField(suffix = "verbatim", type = Keyword)})
    private String title;

    @Field(type = Nested, includeInParent = true)
    private List<Author> authors;

    @Field(type = Keyword)
    private String[] tags;
}
