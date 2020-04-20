package com.htp.controller.convert.users;

import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class UserChangeRequestConverter extends UserRequestConverter<UserUpdateRequest, HibernateUsers> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateUsers convert(UserUpdateRequest request) {
        HibernateUsers user =
                ofNullable(entityManager.find(HibernateUsers.class, request.getUserId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateUsers.class, request.getUserId()));
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return doConvert(user, request);
    }
}