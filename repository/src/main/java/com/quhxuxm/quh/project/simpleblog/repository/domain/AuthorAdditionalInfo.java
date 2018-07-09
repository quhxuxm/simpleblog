package com.quhxuxm.quh.project.simpleblog.repository.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author_additional_info")
public class AuthorAdditionalInfo implements Serializable {
    private static final long serialVersionUID = -4874948031108285296L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "article_number")
    private Long articleNumber;
    @Column(name = "comment_number")
    private Long commentNumber;
    @Column(name = "anthology_number")
    private Long anthologyNumber;
    @Column(name = "followed_by_number")
    private Long followedByNumber;
    @OneToOne(mappedBy = "additionalInfo", fetch = FetchType.LAZY)
    private Author author;

    public AuthorAdditionalInfo() {
        this.articleNumber = 0L;
        this.commentNumber = 0L;
        this.anthologyNumber = 0L;
        this.followedByNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowedByNumber() {
        return followedByNumber;
    }

    public void setFollowedByNumber(Long followedByNumber) {
        this.followedByNumber = followedByNumber;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getAnthologyNumber() {
        return anthologyNumber;
    }

    public void setAnthologyNumber(Long anthologyNumber) {
        this.anthologyNumber = anthologyNumber;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
