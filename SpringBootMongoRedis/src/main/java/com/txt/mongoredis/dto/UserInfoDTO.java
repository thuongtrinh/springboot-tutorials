package com.txt.mongoredis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
