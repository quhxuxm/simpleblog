package com.quhxuxm.quh.project.simpleblog.service.dto;

public class AnthologyBookmarkDTO {
    private Long authorId;
    private Long anthologyId;

    public Long getAuthorId() {
        return authorId;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }
}
