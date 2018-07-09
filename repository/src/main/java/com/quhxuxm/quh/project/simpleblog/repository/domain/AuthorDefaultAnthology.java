package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_default_anthology")
public class AuthorDefaultAnthology implements Serializable {
    private static final long serialVersionUID = -5512420538467327316L;
    @Id
    @OneToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", updatable = false)
    private Long authorId;
    @Id
    @OneToOne(targetEntity = Anthology.class)
    @JoinColumn(name = "anthology_id", referencedColumnName = "id")
    private Long anthologyId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mark_date", nullable = false)
    private Date markDate;

    public AuthorDefaultAnthology() {
        this.markDate = new Date();
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }
}
