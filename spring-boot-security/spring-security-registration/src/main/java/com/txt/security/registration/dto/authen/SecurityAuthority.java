package com.txt.security.registration.dto.authen;

import org.springframework.security.core.GrantedAuthority;

public record SecurityAuthority(String authority) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return authority;
    }
}
