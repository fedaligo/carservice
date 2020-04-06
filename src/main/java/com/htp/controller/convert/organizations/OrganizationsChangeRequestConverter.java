package com.htp.controller.convert.organizations;

import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.exceptions.EntityNotFoundException;

import static java.util.Optional.ofNullable;

public class OrganizationsChangeRequestConverter extends OrganizationsRequestConverter<OrganizationsUpdateRequest, HibernateOrganizations> {
    @Override
    public HibernateOrganizations convert(OrganizationsUpdateRequest request) {
        HibernateOrganizations t =
                ofNullable(entityManager.find(HibernateOrganizations.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateOrganizations.class, request.getId()));
        return doConvert(t, request);
    }
}
