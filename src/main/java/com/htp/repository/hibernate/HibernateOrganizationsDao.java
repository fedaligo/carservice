package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateOrganizations;

public interface HibernateOrganizationsDao extends GenericDao<HibernateOrganizations, Long> {
    public HibernateOrganizations save(HibernateOrganizations entity);
    public HibernateOrganizations updateOne(HibernateOrganizations entity);
    //public HibernateUsers delete(Long id);
}