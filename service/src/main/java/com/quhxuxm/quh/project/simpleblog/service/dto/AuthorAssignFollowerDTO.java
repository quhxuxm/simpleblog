package com.quhxuxm.quh.project.simpleblog.service.dto;
public class AuthorAssignFollowerDTO {
    private Long authorId;
    private Long followerId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
