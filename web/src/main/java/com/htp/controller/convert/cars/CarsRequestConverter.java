package com.htp.controller.convert.cars;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.domain.hibernate.HibernateCars;

public abstract class CarsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateCars doConvert(HibernateCars t, CarsCreateRequest request) {

        //HibernateUsersDao hibernateUsersDao = null;

        t.setCarBrand(request.getCarBrand());
        t.setBrandModel(request.getBrandModel());
        t.setTypeOfTransmission(request.getTypeOfTransmission());
        t.setTypeOfFuel(request.getTypeOfFuel());
        t.setCarWeight(request.getCarWeight());
        t.setVinNumber(request.getVinNumber());
        //t.setUser(hibernateUsersDao.findById(request.getUser_id()));

        return t;
    }
}
