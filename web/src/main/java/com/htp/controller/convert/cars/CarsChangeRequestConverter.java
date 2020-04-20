package com.htp.controller.convert.cars;

import com.htp.controller.requests.cars.CarsUpdateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.repository.hibernate.HibernateUsersDao;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CarsChangeRequestConverter extends CarsRequestConverter<CarsUpdateRequest, HibernateCars> {
    public CarsChangeRequestConverter(HibernateUsersDao hibernateUsersDao) {
        super(hibernateUsersDao);
    }

    @Override
    public HibernateCars convert(CarsUpdateRequest request) {
        HibernateCars t =
                ofNullable(entityManager.find(HibernateCars.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateCars.class, request.getId()));
        return doConvert(t, request);
    }
}
