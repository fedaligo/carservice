package com.htp.controller.convert.tracking;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.domain.enums.Status;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.hibernate.HibernateOrganizationsDao;
import com.htp.repository.hibernate.HibernateTasksDao;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@RequiredArgsConstructor
public abstract class TrackingRequestConverter<S, T> extends EntityConverter<S, T> {

    private final HibernateTasksDao hibernateTasksDao;

    private final HibernateOrganizationsDao hibernateOrganizationsDao;

    protected HibernateTracking doConvert(HibernateTracking t, TrackingCreateRequest request) {
        HibernateOrganizations hibernateOrganizations = null;
        Timestamp timestamp = null;
        Status status = Status.NOT_CONFIRMED;
        HibernateTasks hibernateTasks = hibernateTasksDao.findById(request.getIdTask());

        if(request.getIdOrganaizer()!=null){
        hibernateOrganizations = hibernateOrganizationsDao.findById(request.getIdOrganaizer());
        timestamp = new Timestamp(new Date().getTime());
        status = Status.CONFIRMED;}



        t.setConfirm_date(timestamp);
        t.setCost(request.getCost());
        t.setStatus(status);
        t.setOrganizations(hibernateOrganizations);
        t.setTasks(hibernateTasks);

    return t;
    }
}
