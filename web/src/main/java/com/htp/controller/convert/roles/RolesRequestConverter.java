package com.htp.controller.convert.roles;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;


public abstract class RolesRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateUsers doConvert(HibernateUsers user, UserCreateRequest request) {
        return null;
    }
}
