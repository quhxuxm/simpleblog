package com.quhxuxm.quh.project.simpleblog.domain.view;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationSummary implements Serializable {
    private static final long serialVersionUID = 4116604459336919324L;
    private Long id;
    private Date registerDate;
    private Date lastLoginDate;
    private Authentication.Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Authentication.Type getType() {
        return type;
    }

    public void setType(Authentication.Type type) {
        this.type = type;
    }
}
