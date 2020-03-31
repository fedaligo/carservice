package com.htp.controller.convert;

import com.htp.controller.requests.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class UserChangeRequestConverter extends UserRequestConverter<UserUpdateRequest, HibernateUsers> {

    @Override
    public HibernateUsers convert(UserUpdateRequest request) {
        HibernateUsers user =
                ofNullable(entityManager.find(HibernateUsers.class, request.getUserId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateUsers.class, request.getUserId()));
        return doConvert(user, request);
    }
}