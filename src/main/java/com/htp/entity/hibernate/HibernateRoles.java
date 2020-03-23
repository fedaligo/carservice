package com.htp.entity.hibernate;

import com.htp.entity.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.EnumType.STRING;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode/*(exclude = {
        "userId", "roles", "professions"
})*/
@ToString/*(exclude = {
        "roles", "professions"
})*/
@Entity
@Table(name = "m_roles")
public class HibernateRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long Id;

        @Column
        private String name_of_role;

}
