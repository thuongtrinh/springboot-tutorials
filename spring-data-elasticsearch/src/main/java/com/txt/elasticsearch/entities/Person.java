package com.txt.elasticsearch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "person")
public class Person {

    @Id
    @Field
    private String id;

    @Field
    private int age;

    @Field
    private String fullName;

    @Field
    private Date dateOfBirth;

    public Person(int age, String fullName, Date dateOfBirth) {
        this.age = age;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }
}
