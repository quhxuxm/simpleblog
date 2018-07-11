package com.quhxuxm.quh.project.simpleblog.service.dto;
import com.quhxuxm.quh.project.simpleblog.domain.Authentication;

public class AuthorLoginDTO {
    private String token;
    private String password;
    private Authentication.Type type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authentication.Type getType() {
        return type;
    }

    public void setType(Authentication.Type type) {
        this.type = type;
    }
}
