package com.quhxuxm.quh.project.simpleblog.domain.view;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;

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
    private Set<String> tags;
    private Set<String> roles;
    private Long authenticationId;
    private Authentication.Type authenticationType;
    private Date registerDate;
    private Date lastLoginDate;

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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Long getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(Long authenticationId) {
        this.authenticationId = authenticationId;
    }

    public Authentication.Type getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(Authentication.Type authenticationType) {
        this.authenticationType = authenticationType;
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

}
