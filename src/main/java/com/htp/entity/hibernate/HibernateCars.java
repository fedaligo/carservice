package com.htp.entity.hibernate;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "m_car")
@ToString/*(exclude = {
        "users"
        })*/
public class HibernateCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_Brand")
    private String carBrand;

    @Column(name = "brand_Model")
    private String brandModel;

    @Column(name = "type_Of_Transmission")
    private String typeOfTransmission;

    @Column(name = "type_Of_Fuel")
    private String typeOfFuel;

    @Column(name = "vin_Number")
    private String vinNumber;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "car_Weight")
    private Long carWeight;
}
