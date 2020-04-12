package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateCars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HibernateCarsRepository extends CrudRepository<HibernateCars, Long>, JpaRepository<HibernateCars,Long> {
}
