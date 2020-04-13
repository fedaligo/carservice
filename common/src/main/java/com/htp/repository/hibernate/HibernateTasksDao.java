package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateTasks;

public interface HibernateTasksDao extends GenericDao<HibernateTasks, Long> {
    public HibernateTasks save(HibernateTasks entity);
    public HibernateTasks updateOne(HibernateTasks entity);
}
