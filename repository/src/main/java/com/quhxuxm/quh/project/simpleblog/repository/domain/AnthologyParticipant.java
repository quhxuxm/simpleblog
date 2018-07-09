package com.quhxuxm.quh.project.simpleblog.repository.domain;

import java.io.Serializable;
import java.util.Date;

public class AnthologyParticipant implements Serializable {
    private static final long serialVersionUID = -7732733544433259761L;
    private Long authorId;
    private Long anthologyId;
    private Date participateDate;
    private Boolean deleted;

    public AnthologyParticipant() {
        this.deleted = false;
        this.participateDate = new Date();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public Date getParticipateDate() {
        return participateDate;
    }

    public void setParticipateDate(Date participateDate) {
        this.participateDate = participateDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
