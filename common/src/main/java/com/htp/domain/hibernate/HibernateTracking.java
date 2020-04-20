package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.domain.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

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

    @Enumerated(STRING)
    @Column
    private Status status = Status.NOT_CONFIRMED;

    @Column
    private Date confirm_date;

    @Column
    private Long cost;
}
