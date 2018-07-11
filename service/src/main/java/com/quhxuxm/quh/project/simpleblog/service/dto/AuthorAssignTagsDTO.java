package com.quhxuxm.quh.project.simpleblog.service.dto;
import java.util.Set;

public class AuthorAssignTagsDTO {
    private Long authorId;
    private Set<String> tags;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
