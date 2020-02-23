package com.htp.dao;

import com.htp.dao.GenericDao;
import com.htp.entity.Tracking;
import com.htp.entity.Users;

import java.util.List;

public interface TrackingDao extends GenericDao<Tracking,Long> {
    public List<Tracking> trackingByHigherCost(Long cost);
}
