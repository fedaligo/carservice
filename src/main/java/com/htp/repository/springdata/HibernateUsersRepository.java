package com.htp.repository.springdata;

import com.htp.domain.Gender;
import com.htp.domain.hibernate.HibernateUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

//@Repository
public interface HibernateUsersRepository extends CrudRepository<HibernateUsers, Long>, JpaRepository<HibernateUsers,Long> {

    List<HibernateUsers> findByLoginAndGender(String login, Gender gender);

    @Query("select hu from HibernateUsers hu where hu.userId > :lowerId")
    List<HibernateUsers> test(String lowerId);

    @Query("select u.birthDate from TestUser u where u.login like %:lowerId%")
    List<Timestamp> qweqweqweqw3(@Param("lowerId") String qweqwe);
}
