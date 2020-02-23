package com.htp.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Users {
    private Long id;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
    private String eMail;
    private Long phNumberUser;

    public Users(){

    }

    public Users(Long id, String login, String password, Timestamp created, Timestamp changed, Boolean isDeleted, String eMail, Long phNumberUser) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created = created;
        this.changed = changed;
        this.isDeleted = isDeleted;
        this.eMail = eMail;
        this.phNumberUser = phNumberUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getPhNumberUser() {
        return phNumberUser;
    }

    public void setPhNumberUser(Long phNumberUser) {
        this.phNumberUser = phNumberUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", isDeleted=" + isDeleted +
                ", eMail='" + eMail + '\'' +
                ", phNumberUser=" + phNumberUser +
                '}'+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) &&
                getLogin().equals(users.getLogin()) &&
                getPassword().equals(users.getPassword()) &&
                getCreated().equals(users.getCreated()) &&
                getChanged().equals(users.getChanged()) &&
                isDeleted.equals(users.isDeleted) &&
                geteMail().equals(users.geteMail()) &&
                getPhNumberUser().equals(users.getPhNumberUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getCreated(), getChanged(), isDeleted, geteMail(), getPhNumberUser());
    }
}
