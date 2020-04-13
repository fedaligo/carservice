package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateRoles;

public interface HibernateRolesDao extends GenericDao<HibernateRoles, Long> {
    public HibernateRoles save(HibernateRoles entity);
    public HibernateRoles updateOne(HibernateRoles entity);
}