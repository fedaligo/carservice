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

    /*@Autowired
    @Qualifier("usersDaoImpl")
    private UsersDao usersDao;

    @Autowired
    private RolesDao rolesDao;*/

    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateRolesRepository hibernateRolesRepository;
/*
    private final HibernateUsersRepository hibernateUsersRepository;

    public UserDetailsServiceImpl(HibernateUsersRepository hibernateUsersRepository) {
        this.hibernateUsersRepository = hibernateUsersRepository;
    }

    @Transactional
    public void testCrudRepository(){
        Optional<HibernateUsers> hibernateUsers = hibernateUsersRepository.findById(5l);
    }*/

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = usersDao.findByLogin(username);
            List<Roles> roles = rolesDao.getRolesByUserId(user.getId());
            if (user.getId() == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getNameOfRole())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }*/
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
           Optional<HibernateUsers> searchResult = hibernateUsersRepository.findByLogin(username);
           if (searchResult.isPresent()) {
               HibernateUsers hibernateUsers = searchResult.get();
               return new org.springframework.security.core.userdetails.User(
                       hibernateUsers.getLogin(),
                       hibernateUsers.getPassword(),
                       //AuthorityUtils.commaSeparatedStringToAuthorityList(hibernateUsers.getRole.getNameOfRole())
                       AuthorityUtils.commaSeparatedStringToAuthorityList(hibernateUsers.getRole().stream().
                               map(HibernateRoles::getName_of_role).collect(Collectors.joining(",")))
               );
           } else {
               throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));

           }
       } catch (Exception e) {
           throw new UsernameNotFoundException("User with this login not found");
       }
   }
}