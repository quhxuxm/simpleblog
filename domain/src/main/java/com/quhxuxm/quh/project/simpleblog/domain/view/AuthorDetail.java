package com.quhxuxm.quh.project.simpleblog.domain.view;

import java.io.Serializable;
import java.util.HashSet;

public class AuthorDetail implements Serializable {
    private static final long serialVersionUID = -2593721411811893664L;
    private Long id;
    private Long iconImageId;
    private String description;
    private Long defaultAnthologyId;
    private String nickName;
    private Long publishArticlesNumber;
    private Long publishCommentsNumber;
    private Long publishAnthologiesNumber;
    private Long followedByNumber;

    public AuthorDetail() {
        this.tags = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIconImageId() {
        return iconImageId;
    }

    public void setIconImageId(Long iconImageId) {
        this.iconImageId = iconImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDefaultAnthologyId() {
        return defaultAnthologyId;
    }

    public void setDefaultAnthologyId(Long defaultAnthologyId) {
        this.defaultAnthologyId = defaultAnthologyId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
