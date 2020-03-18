package com.htp.dao.hibernate;

import com.htp.dao.GenericDao;
import com.htp.entity.hibernate.HibernateUsers;

public interface HibernateUsersDao extends GenericDao<HibernateUsers, Long> {
    public HibernateUsers save(HibernateUsers entity);
    public HibernateUsers updateOne(HibernateUsers entity);

}
