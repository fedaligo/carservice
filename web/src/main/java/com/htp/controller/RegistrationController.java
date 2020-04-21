package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.springdata.HibernateOrganizationsRepository;
import com.htp.repository.springdata.HibernateRolesRepository;
import com.htp.repository.springdata.HibernateUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final ConversionService conversionService;

    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateRolesRepository hibernateRolesRepository;

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

    @PostMapping("/user")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<HibernateUsers> createConvertedHibernateUser(@RequestBody @Valid UserCreateRequest request) {
        HibernateUsers savedConvertedUser = hibernateUsersRepository.saveAndFlush(conversionService.convert(request, HibernateUsers.class));
        hibernateRolesRepository.saveAndFlush(new HibernateRoles("ROLE_USER",savedConvertedUser));
        return new ResponseEntity<>( savedConvertedUser, CREATED);
    }

    @PostMapping("/organization")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<HibernateOrganizations> createConvertedHibernateOrganization(@RequestBody @Valid OrganizationsCreateRequest request) {
        HibernateOrganizations savedConverted = hibernateOrganizationsRepository.saveAndFlush(conversionService.convert(request, HibernateOrganizations.class));
        return new ResponseEntity<>( savedConverted, CREATED);
    }
}