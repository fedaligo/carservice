package com.htp.repository.jdbc;

import com.htp.repository.GenericDao;
import com.htp.domain.Tracking;

import java.util.List;

public interface TrackingDao extends GenericDao<Tracking,Long> {
    public List<Tracking> trackingByHigherCost(Long cost);
    public Tracking save(Tracking entity);
    public Tracking updateOne(Tracking entity);
    public void create(Tracking entity);
}
