package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateTracking;
import com.htp.entity.hibernate.HibernateUsers;

public interface HibernateTrackingDao extends GenericDao<HibernateTracking, Long> {
    public HibernateTracking save(HibernateTracking entity);
    public HibernateTracking updateOne(HibernateTracking entity);
    //public HibernateUsers delete(Long id);
}
