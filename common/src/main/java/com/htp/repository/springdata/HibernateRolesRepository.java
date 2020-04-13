package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateRolesRepository extends CrudRepository<HibernateRoles, Long>, JpaRepository<HibernateRoles,Long> {


    @Query(value = "select t.nameOfRole from HibernateRoles t where t.id =: id")
    List<Object[]> findNameOfRoleById(String id);


    @Override
    Optional<HibernateRoles> findById(Long aLong);
}
