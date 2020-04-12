package com.htp.controller.convert.users;

import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserRequestConverter<UserCreateRequest, HibernateUsers> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateUsers convert(UserCreateRequest request) {
        HibernateUsers user = new HibernateUsers();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return doConvert(user, request);
    }
}
