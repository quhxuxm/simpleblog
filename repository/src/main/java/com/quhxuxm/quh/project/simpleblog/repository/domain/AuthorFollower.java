package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author_follower")
public class AuthorFollower implements Serializable {
    private static final long serialVersionUID = -772343683934230934L;
    @Id
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Long authorId;
    @Id
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Long followerId;
    @Temporal(TemporalType.DATE)
    @Column(name = "follow_date", nullable = false, updatable = false)
    private Date followDate;

    public AuthorFollower() {
        this.followDate = new Date();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }
}
