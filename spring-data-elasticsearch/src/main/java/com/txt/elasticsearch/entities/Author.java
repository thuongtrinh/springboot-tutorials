package com.txt.elasticsearch.entities;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Field(type = Text)
    private String name;

}
