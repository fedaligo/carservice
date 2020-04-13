package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

//@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode//(exclude = {"Id", "user_role"})
@ToString//(exclude = {"user_role"})
@Entity
@Table(name = "m_roles")
public class HibernateRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long Id;

        @Column(name = "name_of_role")
        private String nameOfRole;

        /*@OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        @JsonBackReference
        //@MapsId
        private HibernateUsers user_role;*/

        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private HibernateUsers userRole;

        public HibernateRoles(String roleUser, HibernateUsers userId) {
                this.nameOfRole = roleUser;
                this.userRole = userId;
        }
}
