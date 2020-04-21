package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateOrganizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HibernateOrganizationsRepository extends CrudRepository<HibernateOrganizations, Long>, JpaRepository<HibernateOrganizations,Long> {
    Optional<HibernateOrganizations> findByLogin(String login);

    @Query("select hu from HibernateOrganizations hu WHERE hu.login=:login and hu.isDeleted=false")
    Optional<HibernateOrganizations> findByLoginNotDeleted(String login);

    @Modifying
    @Query("UPDATE HibernateOrganizations hu SET hu.isDeleted = true WHERE hu.login=:login")
    void fakeDelete(String login);


}
