package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class Resource implements Serializable {
    private static final long serialVersionUID = -7001454343688484844L;
    private Long id;
    private byte[] content;
    private String mimeType;
    private String md5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
