package com.htp.entity.hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.entity.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

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

        @Column
        private String name;

        @Column(name = "web_site")
        private String web_site;

        @Column
        private Long phone_number;

        @Column
        private String location;

        @Column
        private String working_time;

        @Column
        private String specialize;

        @Column
        private String e_mail;

        @JsonManagedReference
        @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "organizations")
        private Set<HibernateTracking> tracking = Collections.emptySet();
}
