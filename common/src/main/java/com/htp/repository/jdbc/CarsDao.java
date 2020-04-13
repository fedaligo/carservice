package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Cars;

public interface CarsDao extends GenericDao<Cars,Long> {
    public Cars save(Cars entity);
    public void create(Cars entity);
    public Cars updateOne(Cars entity);
}
