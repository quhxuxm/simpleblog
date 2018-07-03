package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class AuthorAdditionalInfo implements Serializable {
    private Long id;
    private Long publishArticlesNumber;
    private Long publishCommentsNumber;
    private Long publishAnthologiesNumber;
    private Long followedByNumber;

    public AuthorAdditionalInfo() {
        this.publishAnthologiesNumber = 0L;
        this.publishArticlesNumber = 0L;
        this.publishCommentsNumber = 0L;
        this.followedByNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublishArticlesNumber() {
        return publishArticlesNumber;
    }

    public void setPublishArticlesNumber(Long publishArticlesNumber) {
        this.publishArticlesNumber = publishArticlesNumber;
    }

    public Long getPublishCommentsNumber() {
        return publishCommentsNumber;
    }

    public void setPublishCommentsNumber(Long publishCommentsNumber) {
        this.publishCommentsNumber = publishCommentsNumber;
    }

    public Long getPublishAnthologiesNumber() {
        return publishAnthologiesNumber;
    }

    public void setPublishAnthologiesNumber(Long publishAnthologiesNumber) {
        this.publishAnthologiesNumber = publishAnthologiesNumber;
    }

    public Long getFollowedByNumber() {
        return followedByNumber;
    }

    public void setFollowedByNumber(Long followedByNumber) {
        this.followedByNumber = followedByNumber;
    }
}
