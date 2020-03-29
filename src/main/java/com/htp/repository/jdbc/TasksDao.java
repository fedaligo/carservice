package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Tasks;

public interface TasksDao extends GenericDao<Tasks,Long> {
    public Tasks save(Tasks entity);
    public Tasks updateOne(Tasks entity);
}
