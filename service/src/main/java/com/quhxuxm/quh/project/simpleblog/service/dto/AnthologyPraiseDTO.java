package com.quhxuxm.quh.project.simpleblog.service.dto;

public class AnthologyPraiseDTO {
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
