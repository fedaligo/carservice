package com.htp.dao;

import com.htp.entity.Roles;

import java.util.List;

public interface RolesDao extends GenericDao<Roles,Long>{
    List<Roles> getRolesByUserId(Long userId);
}
