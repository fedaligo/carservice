package com.htp.controller.convert.organizations;

import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class OrganizationsChangeRequestConverter extends OrganizationsRequestConverter<OrganizationsUpdateRequest, HibernateOrganizations> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateOrganizations convert(OrganizationsUpdateRequest request) {
        HibernateOrganizations t =
                ofNullable(entityManager.find(HibernateOrganizations.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateOrganizations.class, request.getId()));
        t.setLogin(request.getLogin());
        t.setPassword(passwordEncoder.encode(request.getPassword()));
        return doConvert(t, request);
    }
}