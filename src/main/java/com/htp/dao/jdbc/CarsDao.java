package com.htp.dao.jdbc;

import com.htp.dao.GenericDao;
import com.htp.entity.Cars;
import com.htp.entity.Organizations;

public interface CarsDao extends GenericDao<Cars,Long> {
    public Cars save(Cars entity);
    public Cars updateOne(Cars entity);
}
