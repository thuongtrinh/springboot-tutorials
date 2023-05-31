package com.txt.mongoredis.entities.mongodb.dbsecond;

import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableMongoAuditing
@Builder
public class Customer {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String address;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
