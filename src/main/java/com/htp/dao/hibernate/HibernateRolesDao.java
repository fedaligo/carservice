package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateRoles;
import com.htp.entity.hibernate.HibernateTasks;

public interface HibernateRolesDao extends GenericDao<HibernateRoles, Long> {
    public HibernateRoles save(HibernateRoles entity);
    public HibernateRoles updateOne(HibernateRoles entity);
    //public HibernateUsers delete(Long id);
}