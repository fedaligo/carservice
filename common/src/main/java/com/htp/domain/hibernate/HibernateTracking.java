package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.domain.enums.Status;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.util.Date;

import static javax.persistence.EnumType.STRING;


@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organizations"})
@ToString(exclude = {"organizations"})
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
