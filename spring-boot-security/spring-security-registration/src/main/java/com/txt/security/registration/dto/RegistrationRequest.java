package com.txt.security.registration.dto;

import com.txt.security.registration.validation.PasswordMatches;
import com.txt.security.registration.validation.ValidEmail;
import com.txt.security.registration.validation.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    @ValidEmail
    private String email;

    @NotEmpty
    private String birthdate;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    @PasswordMatches
    private String matchPassword;

    private boolean isUsing2FA;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}