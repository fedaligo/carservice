package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateTasks;
import com.htp.entity.hibernate.HibernateTracking;

public interface HibernateTasksDao extends GenericDao<HibernateTasks, Long> {
    public HibernateTasks save(HibernateTasks entity);
    public HibernateTasks updateOne(HibernateTasks entity);
    //public HibernateUsers delete(Long id);
}
