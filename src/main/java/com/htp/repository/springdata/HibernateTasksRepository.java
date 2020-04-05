package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HibernateTasksRepository extends CrudRepository<HibernateTasks, Long>, JpaRepository<HibernateTasks,Long> {
}
