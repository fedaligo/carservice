package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode//(exclude = {"Id","tracking"})
@ToString//(exclude = {"tracking"})
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
