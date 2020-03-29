package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.HibernateTasksDao;
import com.htp.domain.hibernate.HibernateTasks;
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
@Qualifier("hibernateTasksDao")
public class HibernateTasksDaoImpl implements HibernateTasksDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(HibernateTasks entity) {

    }

    @Override
    public List<HibernateTasks> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
        //return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public HibernateTasks save(HibernateTasks entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateTasks.class, newID);
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateTasks.class, entity.getId());
    }

    @Override
    public HibernateTasks updateOne(HibernateTasks entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateTasks.class, entity.getId());
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateTasks.class, entity.getId());
    }

}