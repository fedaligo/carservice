package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateOrganizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HibernateOrganizationsRepository extends CrudRepository<HibernateOrganizations, Long>, JpaRepository<HibernateOrganizations,Long> {
}
