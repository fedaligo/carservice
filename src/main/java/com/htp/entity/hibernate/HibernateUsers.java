package com.htp.entity.hibernate;

import com.htp.entity.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Column
    private Boolean is_deleted;

    @Column
    private String e_mail;

    @Column
    private Long phone_number_user;

    @Enumerated(STRING)
    @Column
    private Gender gender = Gender.NOT_SELECTED;

}
