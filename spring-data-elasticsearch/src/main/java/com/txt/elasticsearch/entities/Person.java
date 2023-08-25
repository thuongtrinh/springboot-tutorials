package com.txt.elasticsearch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int age;

    private String fullName;

    private Date dateOfBirth;

}
