package com.htp.dao.jdbc;

import com.htp.dao.GenericDao;
import com.htp.entity.Organizations;
import com.htp.entity.Roles;

public interface OrganizationsDao extends GenericDao<Organizations,Long> {
    public Organizations save(Organizations entity);
    public Organizations updateOne(Organizations entity);
}
