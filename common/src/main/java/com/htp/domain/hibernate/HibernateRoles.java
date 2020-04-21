package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "m_roles")
public class HibernateRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long Id;

        @Column(name = "name_of_role")
        private String nameOfRole;

        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private HibernateUsers userRole;

        public HibernateRoles(String roleUser, HibernateUsers userId) {
                this.nameOfRole = roleUser;
                this.userRole = userId;
        }
}
