package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@CacheConfig(cacheNames = "tracks")
public interface HibernateTrackingRepository extends CrudRepository<HibernateTracking, Long>, JpaRepository<HibernateTracking,Long> {

    //@Cacheable
    @Override
    List<HibernateTracking> findAll();
}
