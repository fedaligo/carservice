package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "m_tasks_multiple_ids_search", query = "select tu from HibernateTasks tu where tu.id in (:ids)")
@Entity
@Builder
@Table(name = "m_tasks")
@EqualsAndHashCode(exclude = {"id",/*"cars",*/"tracking"})
@ToString(exclude = {"tracking"/*"cars"*/})
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

    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    @JsonBackReference
    //@MapsId
    private HibernateCars cars;*/

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    private HibernateCars cars;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tasks")
    private HibernateTracking tracking;


}
