package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateTracking;

public interface HibernateTrackingDao extends GenericDao<HibernateTracking, Long> {
    public HibernateTracking save(HibernateTracking entity);
    public HibernateTracking updateOne(HibernateTracking entity);
    //public HibernateUsers delete(Long id);
}
