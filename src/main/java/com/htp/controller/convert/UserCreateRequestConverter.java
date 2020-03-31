package com.htp.controller.convert;

import com.htp.controller.requests.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, HibernateUsers> {

    @Override
    public HibernateUsers convert(UserCreateRequest request) {
        HibernateUsers user = new HibernateUsers();
        return doConvert(user, request);
    }
}
