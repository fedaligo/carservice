package com.htp.dao.jdbc;

import com.htp.dao.GenericDao;
import com.htp.entity.Tasks;
import com.htp.entity.Users;

public interface TasksDao extends GenericDao<Tasks,Long> {
    public Tasks save(Tasks entity);
    public Tasks updateOne(Tasks entity);
}
