package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "m_organization")
public class HibernateOrganizations {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long Id;

        @Column(name = "login")
        private String login;

        @Column(name = "password")
        private String password;

        @Column(name = "is_deleted")
        private Boolean isDeleted;

        @JsonIgnore
        @Column(name = "role")
        private String role;

        @Column(name = "web_site")
        private String webSite;

        @Column(name = "phone_number")
        private Long phoneNumber;

        @Column
        private String location;

        @Column(name = "working_time")
        private String workingTime;

        @Column
        private String specialize;

        @Column(name = "e_mail")
        private String eMail;

        @JsonManagedReference
        @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "organizations")
        private Set<HibernateTracking> tracking = Collections.emptySet();
}
