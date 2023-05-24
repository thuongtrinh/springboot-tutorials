package com.txt.mongoredis.entities.mongodb.dbfirst;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("country")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private String id;

    private String code;

    private String name;
}
