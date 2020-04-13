package com.htp.repository.hibernate;

import com.htp.repository.GenericDao;
import com.htp.domain.hibernate.HibernateCars;

public interface HibernateCarsDao extends GenericDao<HibernateCars, Long> {
    public HibernateCars save(HibernateCars entity);
    public HibernateCars updateOne(HibernateCars entity);

}