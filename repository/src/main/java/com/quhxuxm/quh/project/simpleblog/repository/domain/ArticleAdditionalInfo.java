package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_additional_info")
public class ArticleAdditionalInfo implements Serializable {
    private static final long serialVersionUID = -2638419427009756150L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "view_number", nullable = false)
    private Long viewNumber;
    @Column(name = "content_number", nullable = false)
    private Long commentNumber;
    @Column(name = "praise_number", nullable = false)
    private Long praiseNumber;
    @Column(name = "bookmark_number", nullable = false)
    private Long bookmarkNumber;

    public ArticleAdditionalInfo() {
        this.viewNumber = 0L;
        this.commentNumber = 0L;
        this.praiseNumber = 0L;
        this.bookmarkNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }
}
