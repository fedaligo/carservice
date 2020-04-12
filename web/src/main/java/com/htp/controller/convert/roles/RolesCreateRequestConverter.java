package com.htp.controller.convert.roles;

import com.htp.controller.requests.roles.RolesCreateRequest;
import com.htp.domain.hibernate.HibernateRoles;
import org.springframework.stereotype.Component;

@Component
public class RolesCreateRequestConverter extends RolesRequestConverter<RolesCreateRequest, HibernateRoles> {
    @Override
    public HibernateRoles convert(RolesCreateRequest request) {
        HibernateRoles t = new HibernateRoles();
        return t/*doConvert(t, request)*/;
    }
}
