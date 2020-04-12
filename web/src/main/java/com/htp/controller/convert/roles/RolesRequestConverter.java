package com.htp.controller.convert.roles;

import com.htp.controller.convert.EntityConverter;

public abstract class RolesRequestConverter<S, T> extends EntityConverter<S, T> {
    /*protected HibernateUsers doConvert(HibernateUsers user, UserCreateRequest request) {

        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIs_deleted(false);
        user.setE_mail(request.getEMail());
        user.setPhone_number_user(request.getPhNumberUser());

        return user;
    }*/
}
