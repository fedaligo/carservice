package com.htp.controller.convert.tracking;

import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.hibernate.HibernateOrganizationsDao;
import com.htp.repository.hibernate.HibernateTasksDao;
import org.springframework.stereotype.Component;

@Component
public class TrackingCreateRequestConverter extends TrackingRequestConverter<TrackingCreateRequest, HibernateTracking> {
    public TrackingCreateRequestConverter(HibernateTasksDao hibernateTasksDao, HibernateOrganizationsDao hibernateOrganizationsDao) {
        super(hibernateTasksDao, hibernateOrganizationsDao);
    }

    @Override
    public HibernateTracking convert(TrackingCreateRequest request) {
        HibernateTracking t = new HibernateTracking();
        return doConvert(t, request);
    }
}
