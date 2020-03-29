package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Roles;

import java.util.List;

public interface RolesDao extends GenericDao<Roles,Long> {
    List<Roles> getRolesByUserId(Long userId);
    public Roles save(Roles entity);

    public Roles updateOne(Roles entity);
}
