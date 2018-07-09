package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "anthology_additional_info")
public class AnthologyAdditionalInfo implements Serializable {
    private static final long serialVersionUID = 3118971567859072683L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "followup_number", nullable = false)
    private Long followupNumber;
    @Column(name = "comment_number", nullable = false)
    private Long commentNumber;
    @Column(name = "view_number", nullable = false)
    private Long viewNumber;
    @Column(name = "bookmark_number", nullable = false)
    private Long bookmarkNumber;
    @Column(name = "praise_number", nullable = false)
    private Long praiseNumber;
    @Column(name = "article_number", nullable = false)
    private Long articleNumber;

    public AnthologyAdditionalInfo() {
        this.viewNumber = 0L;
        this.commentNumber = 0L;
        this.praiseNumber = 0L;
        this.bookmarkNumber = 0L;
        this.followupNumber = 0L;
        this.articleNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowupNumber() {
        return followupNumber;
    }

    public void setFollowupNumber(Long followupNumber) {
        this.followupNumber = followupNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }
}
