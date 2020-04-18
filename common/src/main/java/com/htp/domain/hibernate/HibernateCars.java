package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.enums.TypeOfFuel;
import com.htp.domain.enums.TypeOfTransmission;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "m_car")
@EqualsAndHashCode(exclude = {"id",/*"user",*/"tasks"})
@ToString(exclude = {"tasks"/*"user"*/})
public class HibernateCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_Brand")
    private String carBrand;

    @Column(name = "brand_Model")
    private String brandModel;

    @Enumerated(STRING)
    @Column(name = "type_Of_transmission")
    private TypeOfTransmission typeOfTransmission = TypeOfTransmission.NOT_SELECTED;

    @Enumerated(STRING)
    @Column(name = "type_Of_fuel")
    private TypeOfFuel typeOfFuel = TypeOfFuel.NOT_SELECTED;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private HibernateUsers user;

    @Column(name = "vin_Number")
    private String vinNumber;

    @Column(name = "car_Weight")
    private Long carWeight;

    /*@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cars")
    private HibernateTasks tasks;*/

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cars")
    private Set<HibernateTasks> tasks = Collections.emptySet();

}
