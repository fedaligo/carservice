package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateUsers;

public interface HibernateUsersDao extends GenericDao<HibernateUsers, Long> {
    public HibernateUsers save(HibernateUsers entity);
    public HibernateUsers updateOne(HibernateUsers entity);
    //public HibernateUsers delete(Long id);

}
