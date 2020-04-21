package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.hibernate.HibernateTasksDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateTasksDao")
public class HibernateTasksDaoImpl implements HibernateTasksDao {

    private final EntityManager entityManager;

    @Override
    public List<HibernateTasks> findAll() {
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateTasks tu order by tu.id", HibernateTasks.class).getResultList();
    }

    @Override
    public HibernateTasks findById(Long id) {
        return entityManager.find(HibernateTasks.class, id);
    }

    @Override
    public List<HibernateTasks> update(HibernateTasks entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.createQuery("select tu from HibernateTasks tu order by tu.id", HibernateTasks.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public HibernateTasks save(HibernateTasks entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateTasks.class, entity.getId());
    }

    @Override
    public HibernateTasks updateOne(HibernateTasks entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateTasks.class, entity.getId());
    }

}