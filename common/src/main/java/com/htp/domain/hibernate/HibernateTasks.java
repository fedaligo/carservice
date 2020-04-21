package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "m_tasks_multiple_ids_search", query = "select tu from HibernateTasks tu where tu.id in (:ids)")
@Entity
@Builder
@Table(name = "m_tasks")
@EqualsAndHashCode(exclude = {"id","tracking"})
@ToString(exclude = {"tracking"})
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    private HibernateCars cars;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tasks")
    private HibernateTracking tracking;


}
