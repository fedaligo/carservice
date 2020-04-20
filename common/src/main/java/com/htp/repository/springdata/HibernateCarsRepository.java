package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateCarsRepository extends CrudRepository<HibernateCars, Long>, JpaRepository<HibernateCars,Long> {

    Optional<HibernateCars> findByCarBrandAndBrandModelAndUser(String brand, String model, HibernateUsers user);

    @Modifying
    @Query("delete from HibernateCars b where b.carBrand=:brand and b.brandModel=:model and b.user=:user")
    void deleteCars(String brand, String model, HibernateUsers user);

    //@Modifying
    @Query("select distinct hc FROM HibernateCars hc join fetch hc.tasks where hc.user=:user ")
    List<HibernateCars> listOfTasks(HibernateUsers user);

    List<HibernateCars> findByUser(HibernateUsers user);
}
