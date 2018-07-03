package com.quhxuxm.quh.project.simpleblog.domain.pojo;
import java.io.Serializable;

public class Author implements Serializable {
    private Long id;
    private Long iconImageId;
    private String description;
    private Long defaultAnthologyId;
    private Long additionalInfoId;
    private String nickName;

    public Author() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDefaultAnthologyId() {
        return defaultAnthologyId;
    }

    public void setDefaultAnthologyId(Long defaultAnthologyId) {
        this.defaultAnthologyId = defaultAnthologyId;
    }

    public Long getAdditionalInfoId() {
        return additionalInfoId;
    }

    public void setAdditionalInfoId(Long additionalInfoId) {
        this.additionalInfoId = additionalInfoId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
