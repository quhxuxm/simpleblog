package com.quhxuxm.quh.project.simpleblog.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_default_anthology")
public class AuthorDefaultAnthology implements Serializable {
    private static final long serialVersionUID = -5512420538467327316L;

    public static class PK implements Serializable {
        @OneToOne(targetEntity = Author.class)
        @JoinColumn(name = "author_id", referencedColumnName = "id", updatable = false)
        private Long authorId;
        @OneToOne(targetEntity = Anthology.class)
        @JoinColumn(name = "anthology_id", referencedColumnName = "id")
        private Long anthologyId;

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
    }

    @EmbeddedId
    private PK pk;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mark_date", nullable = false)
    private Date markDate;

    public AuthorDefaultAnthology() {
        this.markDate = new Date();
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }
}
