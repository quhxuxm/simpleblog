package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class Tag implements Serializable {
    private static final long serialVersionUID = -5345884376453719900L;
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
