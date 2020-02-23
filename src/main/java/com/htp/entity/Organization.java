package com.htp.entity;

import java.util.Objects;

public class Organization {
    private Long id;
    private String name;
    private String webSite;
    private Long phoneNumber;
    private String location;
    private String workingTime;
    private String specialize;
    private String eMail;

    public Organization(){
    }

    public Organization(Long id, String name, String webSite, Long phoneNumber, String location, String workingTime, String specialize, String eMail) {
        this.id = id;
        this.name = name;
        this.webSite = webSite;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.workingTime = workingTime;
        this.specialize = specialize;
        this.eMail = eMail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getWebSite().equals(that.getWebSite()) &&
                getPhoneNumber().equals(that.getPhoneNumber()) &&
                getLocation().equals(that.getLocation()) &&
                getWorkingTime().equals(that.getWorkingTime()) &&
                getSpecialize().equals(that.getSpecialize()) &&
                geteMail().equals(that.geteMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWebSite(), getPhoneNumber(), getLocation(), getWorkingTime(), getSpecialize(), geteMail());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", webSite='" + webSite + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", location='" + location + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", specialize='" + specialize + '\'' +
                ", eMail='" + eMail + '\'' +
                '}'+"\n";
    }
}
