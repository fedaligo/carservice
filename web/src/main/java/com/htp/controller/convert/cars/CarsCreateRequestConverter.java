package com.htp.controller.convert.cars;

import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.repository.hibernate.HibernateUsersDao;
import org.springframework.stereotype.Component;

@Component
public class CarsCreateRequestConverter extends CarsRequestConverter<CarsCreateRequest, HibernateCars> {
    public CarsCreateRequestConverter(HibernateUsersDao hibernateUsersDao) {
        super(hibernateUsersDao);
    }

    @Override
    public HibernateCars convert(CarsCreateRequest request) {
        HibernateCars t = new HibernateCars();
        return doConvert(t, request);
    }
}
