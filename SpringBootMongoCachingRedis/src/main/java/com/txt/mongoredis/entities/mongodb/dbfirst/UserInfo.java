package com.txt.mongoredis.entities.mongodb.dbfirst;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@QueryEntity
@Document(collection = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    private String id;
    private String email;
    private String profile ;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean enabled;
    private String password;
    private String userName;
    private LocalDateTime createDate;
    private String createBy;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
