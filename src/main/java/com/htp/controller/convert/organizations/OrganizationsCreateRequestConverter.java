package com.htp.controller.convert.organizations;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import org.springframework.stereotype.Component;

@Component
public class OrganizationsCreateRequestConverter extends OrganizationsRequestConverter<OrganizationsCreateRequest, HibernateOrganizations> {
    @Override
    public HibernateOrganizations convert(OrganizationsCreateRequest request) {
        HibernateOrganizations t = new HibernateOrganizations();
        return doConvert(t, request);
    }
}