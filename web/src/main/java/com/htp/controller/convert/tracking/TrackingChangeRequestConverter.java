package com.htp.controller.convert.tracking;

import com.htp.controller.requests.tracking.TrackingUpdateRequest;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.exceptions.EntityNotFoundException;

import static java.util.Optional.ofNullable;

public class TrackingChangeRequestConverter extends TrackingRequestConverter<TrackingUpdateRequest, HibernateTracking> {
    @Override
    public HibernateTracking convert(TrackingUpdateRequest request) {
        HibernateTracking t =
                ofNullable(entityManager.find(HibernateTracking.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateTracking.class, request.getId()));
        return doConvert(t, request);
    }
}
