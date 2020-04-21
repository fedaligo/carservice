package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.HibernateUsersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateUserDao")
public class HibernateUsersDaoImpl implements HibernateUsersDao {

    private final EntityManager entityManager;

    @Override
    public List<HibernateUsers> findAll() {
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateUsers tu order by tu.id", HibernateUsers.class).getResultList();
    }

    @Override
    public HibernateUsers findById(Long id) {
        return entityManager.find(HibernateUsers.class, id);
    }

    @Override
    public List<HibernateUsers> update(HibernateUsers entity) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.createQuery("select tu from HibernateUsers tu order by tu.id", HibernateUsers.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {entityManager.remove(findById(id));}

    @Override
    public HibernateUsers save(HibernateUsers entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateUsers.class, entity.getUserId());
    }

    @Override
    public HibernateUsers updateOne(HibernateUsers entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateUsers.class, entity.getUserId());
    }
}
