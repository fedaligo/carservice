package com.htp.controller.convert.users;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;

import java.sql.Timestamp;
import java.util.Date;


public abstract class UserRequestConverter<S, T> extends EntityConverter<S, T> {

    //working like annotation @Slf4g
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserRequestConverter.class);

    protected HibernateUsers doConvert(HibernateUsers user, UserCreateRequest request) {

        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setGender(request.getGender());
        user.setEMail(request.getEMail());
        user.setPhoneNumberUser(request.getPhNumberUser());

        log.info("UserCreateRequest for user with login {}  ", user.getLogin());

        return user;
    }
}