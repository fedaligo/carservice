package com.htp.controller.convert.tracking;

import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.domain.hibernate.HibernateTracking;

public class TrackingCreateRequestConverter extends TrackingRequestConverter<TrackingCreateRequest, HibernateTracking> {
    @Override
    public HibernateTracking convert(TrackingCreateRequest request) {
        HibernateTracking t = new HibernateTracking();
        return doConvert(t, request);
    }
}
