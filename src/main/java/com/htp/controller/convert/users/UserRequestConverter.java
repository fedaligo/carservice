package com.htp.controller.convert.users;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;

import java.sql.Timestamp;
import java.util.Date;

public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateUsers doConvert(HibernateUsers user, UserCreateRequest request) {

        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIs_deleted(false);
        user.setE_mail(request.getEMail());
        user.setPhone_number_user(request.getPhNumberUser());

        return user;
    }
}