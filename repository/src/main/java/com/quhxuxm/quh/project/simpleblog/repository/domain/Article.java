package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 8070388670184979679L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false, length = 1000)
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date")
    private Date publishDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;
    @Lob
    @Column(name = "content", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String content;
    @Column(name = "summary", nullable = false, length = 2000)
    private String summary;
    @ManyToOne(targetEntity = Anthology.class)
    @JoinColumn(name = "anthology_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Long anthologyId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_info_id", referencedColumnName = "id", nullable = false, updatable = false)
    private ArticleAdditionalInfo additionalInfo;
    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "cover_image_id", referencedColumnName = "id")
    private Long coverImageId;
    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    public Article() {
        this.createDate = new Date();
        this.isPublished = false;
        this.additionalInfo = new ArticleAdditionalInfo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArticleAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ArticleAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }
}
