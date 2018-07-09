package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "authentication", uniqueConstraints = {@UniqueConstraint(columnNames = {"token", "type"})})
public class Authentication implements Serializable {
    private static final long serialVersionUID = 3930090769307969321L;

    public enum Type {
        EMAIL,
        WECHAT,
        QQ,
        NETEASE,
        XIAOMI,
        USERNAME
    }

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "token", nullable = false, updatable = false)
    private String token;
    @Column(name = "password", nullable = false)
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "register_date", nullable = false, updatable = false)
    private Date registerDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_login_date")
    private Date lastLoginDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", updatable = false)
    private Author author;
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    public Authentication() {
        this.isEnabled = true;
        this.registerDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enable) {
        this.isEnabled = enable;
    }
}
