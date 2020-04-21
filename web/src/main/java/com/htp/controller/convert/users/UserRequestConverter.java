package com.htp.controller.convert.users;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@RequiredArgsConstructor
public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    protected HibernateUsers doConvert(HibernateUsers user, UserCreateRequest request) {

        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setGender(request.getGender());
        user.setEMail(request.getEMail());
        user.setPhoneNumberUser(request.getPhNumberUser());

        return user;
    }
}