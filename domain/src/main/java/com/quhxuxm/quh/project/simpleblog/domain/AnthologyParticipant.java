package com.quhxuxm.quh.project.simpleblog.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "anthology_participant")
public class AnthologyParticipant implements Serializable {
    private static final long serialVersionUID = -7732733544433259761L;
    @Id
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Long authorId;
    @Id
    @ManyToOne(targetEntity = Anthology.class)
    @JoinColumn(name = "anthology_id", nullable = false, updatable = false)
    private Long anthologyId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "participate_date", nullable = false, updatable = false)
    private Date participateDate;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public AnthologyParticipant() {
        this.isDeleted = false;
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
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }
}
