package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateOrganizations;
import com.htp.entity.hibernate.HibernateRoles;

public interface HibernateOrganizationsDao extends GenericDao<HibernateOrganizations, Long> {
    public HibernateOrganizations save(HibernateOrganizations entity);
    public HibernateOrganizations updateOne(HibernateOrganizations entity);
    //public HibernateUsers delete(Long id);
}