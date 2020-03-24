package com.htp.entity.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.htp.entity.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.EnumType.STRING;

@Data
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

        @Column
        private String name_of_role;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        @JsonBackReference
        //@MapsId
        private HibernateUsers user_role;

}
