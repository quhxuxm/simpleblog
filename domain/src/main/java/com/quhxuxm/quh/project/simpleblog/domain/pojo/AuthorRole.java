package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class AuthorRole implements Serializable {
    private static final long serialVersionUID = 9013859839485014270L;
    private Long authorId;
    private Long roleId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
