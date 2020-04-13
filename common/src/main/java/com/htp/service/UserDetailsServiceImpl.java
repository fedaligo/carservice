package com.htp.service;

import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.springdata.HibernateRolesRepository;
import com.htp.repository.springdata.HibernateUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service//(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateRolesRepository hibernateRolesRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
           Optional<HibernateUsers> searchResult = hibernateUsersRepository.findByLogin(username);
           if (searchResult.isPresent()) {
               HibernateUsers hibernateUsers = searchResult.get();
               return new org.springframework.security.core.userdetails.User(
                       hibernateUsers.getLogin(),
                       hibernateUsers.getPassword(),
                       AuthorityUtils.commaSeparatedStringToAuthorityList(hibernateUsers.getRole().stream().
                               map(HibernateRoles::getNameOfRole).collect(Collectors.joining(",")))
               );
           } else {
               throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));

           }
       } catch (Exception e) {
           throw new UsernameNotFoundException("User with this login not found");
       }
   }
}