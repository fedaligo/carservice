package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HibernateTrackingRepository extends CrudRepository<HibernateTracking, Long>, JpaRepository<HibernateTracking,Long> {

}
