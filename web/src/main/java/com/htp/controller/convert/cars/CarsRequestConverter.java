package com.htp.controller.convert.cars;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.HibernateUsersDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CarsRequestConverter<S, T> extends EntityConverter<S, T> {

    private final HibernateUsersDao hibernateUsersDao;

    protected HibernateCars doConvert(HibernateCars t, CarsCreateRequest request) {


        HibernateUsers hibernateUsers = hibernateUsersDao.findById(request.getUserId());

        t.setCarBrand(request.getCarBrand());
        t.setBrandModel(request.getBrandModel());
        t.setTypeOfTransmission(request.getTypeOfTransmission());
        t.setTypeOfFuel(request.getTypeOfFuel());
        t.setCarWeight(request.getCarWeight());
        t.setVinNumber(request.getVinNumber());
        t.setUser(hibernateUsers);

        return t;
    }
}
