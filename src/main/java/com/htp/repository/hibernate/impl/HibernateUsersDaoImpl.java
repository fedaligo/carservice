package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.hibernate.HibernateUsersDao;
import com.htp.domain.hibernate.HibernateUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("hibernateUserDao")
public class HibernateUsersDaoImpl implements HibernateUsersDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(HibernateUsers entity) {

    }

    @Override
    public List<HibernateUsers> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
            //return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {entityManager.remove(findById(id));}


    @Override
    public HibernateUsers save(HibernateUsers entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newUserID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateUsers.class, newUserID);
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateUsers.class, entity.getUserId());
    }

    @Override
    public HibernateUsers updateOne(HibernateUsers entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateUsers.class, entity.getUserId());
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateUsers.class, entity.getUserId());
    }


}
