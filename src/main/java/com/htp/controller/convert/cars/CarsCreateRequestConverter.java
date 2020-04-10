package com.htp.controller.convert.cars;

import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.domain.hibernate.HibernateCars;
import org.springframework.stereotype.Component;

@Component
public class CarsCreateRequestConverter extends CarsRequestConverter<CarsCreateRequest, HibernateCars> {
    @Override
    public HibernateCars convert(CarsCreateRequest request) {
        HibernateCars t = new HibernateCars();
        return doConvert(t, request);
    }
}
