package com.htp.controller.convert.organizations;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationsCreateRequestConverter extends OrganizationsRequestConverter<OrganizationsCreateRequest, HibernateOrganizations> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateOrganizations convert(OrganizationsCreateRequest request) {
        HibernateOrganizations t = new HibernateOrganizations();
        t.setLogin(request.getLogin());
        t.setPassword(passwordEncoder.encode(request.getPassword()));
        return doConvert(t, request);
    }
}
