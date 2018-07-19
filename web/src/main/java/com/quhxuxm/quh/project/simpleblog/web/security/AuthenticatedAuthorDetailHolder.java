package com.quhxuxm.quh.project.simpleblog.web.security;

import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;

public class AuthenticatedAuthorDetailHolder {
    private static final ThreadLocal<AuthorDetailDTO> HOLDER = new ThreadLocal<>();
    public static AuthenticatedAuthorDetailHolder INSTANCE = new AuthenticatedAuthorDetailHolder();

    private AuthenticatedAuthorDetailHolder() {
    }

    public AuthorDetailDTO get() {
        return HOLDER.get();
    }

    public void set(AuthorDetailDTO authorDetail) {
        HOLDER.set(authorDetail);
    }

    public void clear() {
        HOLDER.remove();
    }
}
