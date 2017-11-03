package com.tongwen.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Author implements Serializable {
    private Long id;
    private Long iconImageId;
    private String description;
    private Long defaultAnthologyId;

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

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", iconImageId=" + iconImageId
                + ", description='" + description + '\''
                + ", defaultAnthologyId=" + defaultAnthologyId + '}';
    }
}
