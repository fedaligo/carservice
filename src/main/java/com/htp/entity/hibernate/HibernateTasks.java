package com.htp.entity.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "m_tasks")
@ToString/*(exclude = {
        "users"
})*/
public class HibernateTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_Work_Name")
    private String serviceWorkName;

    @Column(name = "necessity_Of_Evacuation")
    private Boolean necessityOfEvacuation;

    @Column(name = "wheel_Brake")
    private Boolean wheelBrake;

    @Column(name = "created")
    private Date created;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "local_Description")
    private String localDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    @JsonBackReference
    @MapsId
    private HibernateCars hibernateCars;
}
