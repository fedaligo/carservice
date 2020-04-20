package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateTasksRepository extends CrudRepository<HibernateTasks, Long>, JpaRepository<HibernateTasks,Long> {

    @Modifying
    @Query("delete from HibernateTasks b where b.cars=:car and b.serviceWorkName=:serviceworkname")
    void deleteTasks(String serviceworkname, HibernateCars car);

    List<HibernateTasks> findByCars(HibernateCars car);

    Optional<HibernateTasks> findByCarsAndServiceWorkName(HibernateCars car, String serviceWorkName);
}
