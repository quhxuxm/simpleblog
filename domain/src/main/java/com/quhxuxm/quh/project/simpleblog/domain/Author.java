package com.quhxuxm.quh.project.simpleblog.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author implements Serializable {
    private static final long serialVersionUID = -2652995801468036436L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "icon_image_id")
    private Long iconImageId;
    @Column(name = "description")
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "additional_info_id", unique = true, updatable = false, nullable = false,
            referencedColumnName = "id")
    private AuthorAdditionalInfo additionalInfo;
    @Column(name = "nick_name", nullable = false, unique = true, length = 64)
    private String nickName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_role", joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Set<Role> roles;
    @OneToOne(targetEntity = Anthology.class)
    @JoinColumn(name = "default_anthology_id", nullable = false, referencedColumnName = "id")
    private Long defaultAnthologyId;

    public Author() {
        this.additionalInfo = new AuthorAdditionalInfo();
    }

    public AuthorAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AuthorAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getIconImageId() {
        return iconImageId;
    }

    public void setIconImageId(Long iconImageId) {
        this.iconImageId = iconImageId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getDefaultAnthologyId() {
        return defaultAnthologyId;
    }

    public void setDefaultAnthologyId(Long defaultAnthologyId) {
        this.defaultAnthologyId = defaultAnthologyId;
    }
}
