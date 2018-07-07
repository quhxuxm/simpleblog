package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class AuthorAdditionalInfo implements Serializable {
    private static final long serialVersionUID = -4874948031108285296L;
    private Long id;
    private Long articlesNumber;
    private Long commentsNumber;
    private Long anthologiesNumber;
    private Long followedByNumber;

    public AuthorAdditionalInfo() {
        this.articlesNumber = 0L;
        this.commentsNumber = 0L;
        this.anthologiesNumber = 0L;
        this.followedByNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowedByNumber() {
        return followedByNumber;
    }

    public void setFollowedByNumber(Long followedByNumber) {
        this.followedByNumber = followedByNumber;
    }

    public Long getArticlesNumber() {
        return articlesNumber;
    }

    public void setArticlesNumber(Long articlesNumber) {
        this.articlesNumber = articlesNumber;
    }

    public Long getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Long commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public Long getAnthologiesNumber() {
        return anthologiesNumber;
    }

    public void setAnthologiesNumber(Long anthologiesNumber) {
        this.anthologiesNumber = anthologiesNumber;
    }

}
