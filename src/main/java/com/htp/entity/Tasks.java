package com.htp.entity;

import java.util.Date;
import java.util.Objects;

public class Tasks {
    private Long id;
    private String serviceWorkName;
    private Boolean necessityOfEvacuation;
    private Boolean wheelBrake;
    private Long idCar;
    private Date created;
    private String description;
    private Double latitude;
    private Double longitude;
    private String localDescription;

    public Tasks(){
    }

    public Tasks(Long id, String serviceWorkName, Boolean necessityOfEvacuation, Boolean wheelBrake, Long idCar, Date created, String description, Double latitude, Double longitude, String localDescription) {
        this.id = id;
        this.serviceWorkName = serviceWorkName;
        this.necessityOfEvacuation = necessityOfEvacuation;
        this.wheelBrake = wheelBrake;
        this.idCar = idCar;
        this.created = created;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDescription = localDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceWorkName() {
        return serviceWorkName;
    }

    public void setServiceWorkName(String serviceWorkName) {
        this.serviceWorkName = serviceWorkName;
    }

    public Boolean getNecessityOfEvacuation() {
        return necessityOfEvacuation;
    }

    public void setNecessityOfEvacuation(Boolean necessityOfEvacuation) {
        this.necessityOfEvacuation = necessityOfEvacuation;
    }

    public Boolean getWheelBrake() {
        return wheelBrake;
    }

    public void setWheelBrake(Boolean wheelBrake) {
        this.wheelBrake = wheelBrake;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocalDescription() {
        return localDescription;
    }

    public void setLocalDescription(String localDescription) {
        this.localDescription = localDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tasks)) return false;
        Tasks tasks = (Tasks) o;
        return getId().equals(tasks.getId()) &&
                getServiceWorkName().equals(tasks.getServiceWorkName()) &&
                getNecessityOfEvacuation().equals(tasks.getNecessityOfEvacuation()) &&
                getWheelBrake().equals(tasks.getWheelBrake()) &&
                getIdCar().equals(tasks.getIdCar()) &&
                getCreated().equals(tasks.getCreated()) &&
                getDescription().equals(tasks.getDescription()) &&
                getLatitude().equals(tasks.getLatitude()) &&
                getLongitude().equals(tasks.getLongitude()) &&
                getLocalDescription().equals(tasks.getLocalDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getServiceWorkName(), getNecessityOfEvacuation(), getWheelBrake(), getIdCar(), getCreated(), getDescription(), getLatitude(), getLongitude(), getLocalDescription());
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", serviceWorkName='" + serviceWorkName + '\'' +
                ", necessityOfEvacuation=" + necessityOfEvacuation +
                ", wheelBrake=" + wheelBrake +
                ", idCar=" + idCar +
                ", created=" + created +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", localDescription='" + localDescription + '\'' +
                '}'+ "\n";
    }
}
