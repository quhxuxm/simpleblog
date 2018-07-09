package com.quhxuxm.quh.project.simpleblog.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class Authentication implements Serializable {
    private static final long serialVersionUID = 3930090769307969321L;

    public enum Type {
        EMAIL, WECHAT, QQ, NETEASE, XIAOMI, USERNAME
    }

    private Long id;
    private String token;
    private String password;
    private Date registerDate;
    private Date lastLoginDate;
    private Type type;
    private Long authorId;
    private Boolean enable;

    public Authentication() {
        this.enable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
