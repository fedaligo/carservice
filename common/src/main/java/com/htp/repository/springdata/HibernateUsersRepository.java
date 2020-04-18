package com.htp.repository.springdata;

import com.htp.domain.enums.Gender;
import com.htp.domain.hibernate.HibernateUsers;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "users")
public interface HibernateUsersRepository extends CrudRepository<HibernateUsers, Long>, JpaRepository<HibernateUsers,Long> {

    List<HibernateUsers> findByLoginAndGender(String login, Gender gender);

    @Cacheable
    @Override
    List<HibernateUsers> findAll();


    @Query("select hu from HibernateUsers hu ")
    List<HibernateUsers> test();

    Optional<HibernateUsers> findByLogin(String login);

   /* @Query("select u.birthDate from TestUser u where u.login like %:lowerId%")
    List<Timestamp> qweqweqweqw3(@Param("lowerId") String qweqwe);*/
}
