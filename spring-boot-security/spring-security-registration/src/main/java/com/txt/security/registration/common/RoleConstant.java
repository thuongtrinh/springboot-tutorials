package com.txt.security.registration.common;

import java.util.stream.Stream;

public enum RoleConstant {

    SYS_ADMIN("SYS-ADMIN", "SYS Admin"),
    SYS_USER("SYS-USER", "SYS User");

    public final String code;
    public final String name;

    RoleConstant(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String[] allowAccessProcessInstanceApiRoles() {
        return Stream.of(SYS_USER.code, SYS_ADMIN.code).toArray(String[]::new);
    }

}
