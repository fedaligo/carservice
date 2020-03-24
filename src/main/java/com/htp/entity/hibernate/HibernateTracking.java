package com.htp.entity.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organizations"/*,"tasks"*/})
@ToString(exclude = {"organizations"/*,"tasks"*/})
@Entity
@Table(name = "tracking_system")
public class HibernateTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_task")
    @JsonBackReference
    //@MapsId
    private HibernateTasks tasks;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_organaizer")
    private HibernateOrganizations organizations;

    @Column
    private String status;

    @Column
    private Date confirm_date;

    @Column
    private Long cost;
}
