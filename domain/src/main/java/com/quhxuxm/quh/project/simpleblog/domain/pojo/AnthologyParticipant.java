package com.quhxuxm.quh.project.simpleblog.domain.pojo;
import java.util.Date;

public class AnthologyParticipant {
    private Long authorId;
    private Long anthologyId;
    private Date participateDate;

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
}
