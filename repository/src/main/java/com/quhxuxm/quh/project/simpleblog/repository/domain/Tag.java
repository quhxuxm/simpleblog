package com.quhxuxm.quh.project.simpleblog.repository.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = -5345884376453719900L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "text", unique = true, nullable = false, length = 64)
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
