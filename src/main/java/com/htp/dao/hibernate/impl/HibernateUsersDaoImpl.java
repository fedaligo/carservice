package com.htp.dao.hibernate.impl;

import com.htp.dao.hibernate.HibernateUsersDao;
import com.htp.entity.hibernate.HibernateUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("hibernateUserDao")
public class HibernateUsersDaoImpl implements HibernateUsersDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    @Override
    public void create(HibernateUsers entity) {

    }

    @Override
    public List<HibernateUsers> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        System.out.println(entityManager.toString());
//        return entityManager.createQuery("select tu from TestUser tu", TestUser.class).getResultList();
    }

    @Override
    public HibernateUsers findById(Long id) {
        return null;
    }

    @Override
    public List<HibernateUsers> update(HibernateUsers entity) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public HibernateUsers save(HibernateUsers entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newUserID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateUsers.class, newUserID);
        }
    }

    @Override
    public HibernateUsers updateOne(HibernateUsers entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateUsers.class, entity.getUserId());
        }
    }


}
