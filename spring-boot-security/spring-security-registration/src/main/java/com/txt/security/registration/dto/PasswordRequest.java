package com.txt.security.registration.dto;

import com.txt.security.registration.validation.PasswordMatches;
import com.txt.security.registration.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatches
public class PasswordRequest {

    private String token;

    @NotBlank
    private String oldPassword;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotBlank
    private String matchPassword;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
