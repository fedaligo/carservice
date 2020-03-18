package com.htp.dao.jdbc;

import com.htp.dao.GenericDao;
import com.htp.entity.Roles;
import com.htp.entity.Users;

import java.util.List;

public interface RolesDao extends GenericDao<Roles,Long> {
    List<Roles> getRolesByUserId(Long userId);
    public Roles save(Roles entity);
}
