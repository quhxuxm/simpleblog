package com.quhxuxm.quh.project.simpleblog.repository.domain;

import java.io.Serializable;

public class AuthorDefaultAnthology implements Serializable {
    private static final long serialVersionUID = -5512420538467327316L;
    private Long authorId;
    private Long anthologyId;

    public Long getAnthologyId() {
        return anthologyId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
