package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

//import org.springframework.context.annotation.Configuration;

//@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"userId","cars","role"})
@ToString(exclude = {"cars","role"})
@NamedQuery(name = "m_users_multiple_ids_search", query = "select tu from HibernateUsers tu where tu.userId in (:userIds)")
@Entity
@Table(name = "m_users")
/*@Configuration
@ConfigurationProperties("hibernateusers")*/
public class HibernateUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column
    private String login;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @JsonIgnore
    @Column
    private Boolean is_deleted;

    @JsonIgnore
    @Column
    private String e_mail;

    @JsonIgnore
    @Column
    private Long phone_number_user;

    @Enumerated(STRING)
    @Column
    private Gender gender = Gender.NOT_SELECTED;

   /* @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user_role")
    private HibernateRoles role;*/

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user_role")
    private Set<HibernateRoles> role = Collections.emptySet();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<HibernateCars> cars = Collections.emptySet();

    /*@JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user_car")
    private HibernateCars car;*/

}
