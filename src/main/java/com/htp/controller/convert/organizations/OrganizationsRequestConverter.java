package com.htp.controller.convert.organizations;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;

public abstract class OrganizationsRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateOrganizations doConvert(HibernateOrganizations t, OrganizationsCreateRequest request) {

        t.setName(request.getName());
        t.setWeb_site(request.getWebSite());
        t.setPhone_number(request.getPhoneNumber());
        t.setLocation(request.getLocation());
        t.setWorking_time(request.getWorkingTime());
        t.setSpecialize(request.getSpecialize());
        t.setE_mail(request.getE_Mail());

        return t;
    }
}
