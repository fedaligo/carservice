package com.htp.repository.jdbc;

import com.htp.domain.Tasks;
import com.htp.repository.GenericDao;

public interface TasksDao extends GenericDao<Tasks,Long> {
    public Tasks save(Tasks entity);
    public Tasks updateOne(Tasks entity);
    public void create(Tasks entity);
}
