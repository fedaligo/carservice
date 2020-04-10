package com.htp.controller.convert.tracking;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.domain.hibernate.HibernateTracking;

import java.sql.Timestamp;
import java.util.Date;

public abstract class TrackingRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateTracking doConvert(HibernateTracking t, TrackingCreateRequest request) {

        //HibernateTasksDao hibernateTasksDao = null;

        //HibernateOrganizationsDao hibernateOrganizationsDao = null;

        t.setConfirm_date(new Timestamp(new Date().getTime()));
        t.setCost(request.getCost());
        t.setStatus(request.getStatus());
        //t.setOrganizations(hibernateOrganizationsDao.findById(request.getId_organaizer()));
        //t.setTasks(hibernateTasksDao.findById(request.getId_task()));

    return t;
}
}
