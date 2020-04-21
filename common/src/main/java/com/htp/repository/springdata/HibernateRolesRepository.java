package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HibernateRolesRepository extends CrudRepository<HibernateRoles, Long>, JpaRepository<HibernateRoles,Long> {

    @Override
    Optional<HibernateRoles> findById(Long aLong);
}
