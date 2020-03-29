package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Organizations;

public interface OrganizationsDao extends GenericDao<Organizations,Long> {
    public Organizations save(Organizations entity);
    public Organizations updateOne(Organizations entity);
}
