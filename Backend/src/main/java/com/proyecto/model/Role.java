package com.proyecto.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER(Code.USER),
    ADMIN(Code.ADMIN);

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
