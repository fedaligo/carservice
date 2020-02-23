package com.htp.entity;

import java.util.Objects;

public class Roles {
    private Long id;
    private String nameOfRole;
    private Long userId;

    public Roles(){
    }

    public Roles(Long id, String nameOfRole, Long userId) {
        this.id = id;
        this.nameOfRole = nameOfRole;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        Roles roles = (Roles) o;
        return getId().equals(roles.getId()) &&
                getNameOfRole().equals(roles.getNameOfRole()) &&
                getUserId().equals(roles.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameOfRole(), getUserId());
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", nameOfRole='" + nameOfRole + '\'' +
                ", userId=" + userId +
                '}'+"\n";
    }
}
