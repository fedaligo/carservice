package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.enums.Gender;
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
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.EnumType.STRING;


@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"userId","cars","role"})
@ToString(exclude = {"cars","role"})
@Entity
@Table(name = "m_users")

public class HibernateUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "phone_number_user")
    private Long phoneNumberUser;

    @Enumerated(STRING)
    @Column
    private Gender gender = Gender.NOT_SELECTED;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userRole")
    private Set<HibernateRoles> role = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<HibernateCars> cars = Collections.emptySet();
}
