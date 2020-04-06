package com.htp.controller.convert.roles;

import com.htp.controller.requests.roles.RolesUpdateRequest;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.exceptions.EntityNotFoundException;

import static java.util.Optional.ofNullable;

public class RolesChangeRequestConverter extends RolesRequestConverter<RolesUpdateRequest, HibernateRoles> {
    @Override
    public HibernateRoles convert(RolesUpdateRequest request) {
        HibernateRoles t =
                ofNullable(entityManager.find(HibernateRoles.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateRoles.class, request.getId()));
        return t/*doConvert(t, request)*/;
    }
}
