package com.htp.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Cars {
    private Long id;
    private String carBrand;
    private String brandModel;
    private String typeOfTransmission;
    private String typeOfFuel;
    private String vinNumber;
    private Long userId;
    private Long carWeight;

    public Cars(){

    }

    public Cars(Long id, String carBrand, String brandModel, String typeOfTransmission, String typeOfFuel, String vinNumber, Long userId, Long carWeight) {
        this.id = id;
        this.carBrand = carBrand;
        this.brandModel = brandModel;
        this.typeOfTransmission = typeOfTransmission;
        this.typeOfFuel = typeOfFuel;
        this.vinNumber = vinNumber;
        this.userId = userId;
        this.carWeight = carWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getTypeOfTransmission() {
        return typeOfTransmission;
    }

    public void setTypeOfTransmission(String typeOfTransmission) {
        this.typeOfTransmission = typeOfTransmission;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(Long carWeight) {
        this.carWeight = carWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cars)) return false;
        Cars cars = (Cars) o;
        return getId().equals(cars.getId()) &&
                getCarBrand().equals(cars.getCarBrand()) &&
                getBrandModel().equals(cars.getBrandModel()) &&
                getTypeOfTransmission().equals(cars.getTypeOfTransmission()) &&
                getTypeOfFuel().equals(cars.getTypeOfFuel()) &&
                getVinNumber().equals(cars.getVinNumber()) &&
                getUserId().equals(cars.getUserId()) &&
                getCarWeight().equals(cars.getCarWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCarBrand(), getBrandModel(), getTypeOfTransmission(), getTypeOfFuel(), getVinNumber(), getUserId(), getCarWeight());
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", carBrand='" + carBrand + '\'' +
                ", brandModel='" + brandModel + '\'' +
                ", typeOfTransmission='" + typeOfTransmission + '\'' +
                ", typeOfFuel='" + typeOfFuel + '\'' +
                ", vinNumber='" + vinNumber + '\'' +
                ", userId=" + userId +
                ", carWeight=" + carWeight +
                '}'+"\n";
    }
}
