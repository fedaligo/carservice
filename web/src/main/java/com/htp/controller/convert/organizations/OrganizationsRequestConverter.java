package com.htp.controller.convert.organizations;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;

public abstract class OrganizationsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateOrganizations doConvert(HibernateOrganizations t, OrganizationsCreateRequest request) {

        t.setRole("ROLE_ORG");
        t.setWebSite(request.getWebSite());
        t.setPhoneNumber(request.getPhoneNumber());
        t.setLocation(request.getLocation());
        t.setWorkingTime(request.getWorkingTime());
        t.setSpecialize(request.getSpecialize());
        t.setEMail(request.getEMail());

        return t;
    }
}
