package com.htp.service;

import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.springdata.HibernateOrganizationsRepository;
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

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

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
               Optional<HibernateOrganizations> searchResultNew = hibernateOrganizationsRepository.findByLogin(username);
               if (searchResultNew.isPresent()) {
                   HibernateOrganizations hibernateOrganizations = searchResultNew.get();
                   return new org.springframework.security.core.userdetails.User(
                           hibernateOrganizations.getLogin(),
                           hibernateOrganizations.getPassword(),
                           AuthorityUtils.commaSeparatedStringToAuthorityList(hibernateOrganizations.getRole())
                   );
               } else {
                   throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
               }
           }
       } catch (Exception e) {
           throw new UsernameNotFoundException("User with this login not found");
       }
   }
}