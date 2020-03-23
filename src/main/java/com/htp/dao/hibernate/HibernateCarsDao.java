package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateCars;
import com.htp.entity.hibernate.HibernateOrganizations;

public interface HibernateCarsDao extends GenericDao<HibernateCars, Long> {
    public HibernateCars save(HibernateCars entity);
    public HibernateCars updateOne(HibernateCars entity);
    //public HibernateUsers delete(Long id);
}