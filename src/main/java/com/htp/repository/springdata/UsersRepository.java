package com.htp.repository.springdata;

import com.htp.domain.Gender;
import com.htp.domain.hibernate.HibernateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UsersRepository extends CrudRepository<HibernateUsers, Long>, JpaRepository<HibernateUsers,Long> {

    List<HibernateUsers> findByLoginAndGender(String login, Gender gender);

    @Query("select hu from HibernateUsers hu where hu.userId > :lowerId")
    List<HibernateUsers> test(String lowerId);

    @Query("select u.birthDate from TestUser u where u.login like %:lowerId%")
    List<Timestamp> qweqweqweqw3(@Param("lowerId") String qweqwe);
}
