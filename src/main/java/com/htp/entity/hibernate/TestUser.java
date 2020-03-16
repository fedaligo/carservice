package com.htp.entity.hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "m_users")
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column
    private Boolean is_deleted;

    @Column
    private String e_mail;

    @Column
    private Long phone_number_user;

    public TestUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Long getPhone_number_user() {
        return phone_number_user;
    }

    public void setPhone_number_user(Long phone_number_user) {
        this.phone_number_user = phone_number_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestUser)) return false;
        TestUser testUser = (TestUser) o;
        return Objects.equals(getLogin(), testUser.getLogin()) &&
                Objects.equals(getPassword(), testUser.getPassword()) &&
                Objects.equals(getCreated(), testUser.getCreated()) &&
                Objects.equals(getChanged(), testUser.getChanged()) &&
                Objects.equals(getIs_deleted(), testUser.getIs_deleted()) &&
                Objects.equals(getE_mail(), testUser.getE_mail()) &&
                Objects.equals(getPhone_number_user(), testUser.getPhone_number_user());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword(), getCreated(), getChanged(), getIs_deleted(), getE_mail(), getPhone_number_user());
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", is_deleted=" + is_deleted +
                ", e_mail='" + e_mail + '\'' +
                ", phone_number_user=" + phone_number_user +
                '}';
    }
}
