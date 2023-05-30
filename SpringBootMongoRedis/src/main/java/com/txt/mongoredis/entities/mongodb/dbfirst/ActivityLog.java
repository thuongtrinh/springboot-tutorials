package com.txt.mongoredis.entities.mongodb.dbfirst;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "activity_log")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@EnableMongoAuditing
public class ActivityLog {

    @Builder
    public ActivityLog(String user, String data) {
        this.user = user;
        this.data = data;
        this.createdDate = LocalDateTime.now();
    }

    @Id
    private String id;

    @Field
    @NotNull
    private String data;

    @Field
    @NotNull
    private String status;

    @Field
    @NotNull
    private String user;

    @Field
    @CreatedDate
    private LocalDateTime createdDate;

    @Field
    @CreatedBy
    private String createdBy;

    @Field
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Field
    @LastModifiedBy
    private String updatedBy;

//    @Field
//    @JsonIgnore
//    private String version;
}

