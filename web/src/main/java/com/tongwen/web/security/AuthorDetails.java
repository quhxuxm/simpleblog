package com.tongwen.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthorDetails extends User {
    private Long authenticationId;

    public AuthorDetails(String username, String password,
            Collection<? extends GrantedAuthority> authorities,
            Long authenticationId) {
        super(username, password, authorities);
        this.authenticationId = authenticationId;
    }

    public AuthorDetails(String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            Long authenticationId) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.authenticationId = authenticationId;
    }

    public Long getAuthenticationId() {
        return authenticationId;
    }
}
