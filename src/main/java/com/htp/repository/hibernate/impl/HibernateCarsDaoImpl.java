package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.repository.hibernate.HibernateCarsDao;
import com.htp.domain.hibernate.HibernateCars;
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
@Qualifier("hibernateCarsDao")
public class HibernateCarsDaoImpl implements HibernateCarsDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(HibernateCars entity) {

    }

    @Override
    public List<HibernateCars> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateCars tu order by tu.id", HibernateCars.class).getResultList();
    }

    @Override
    public HibernateCars findById(Long id) {
        return entityManager.find(HibernateCars.class, id);
    }

    @Override
    public List<HibernateCars> update(HibernateCars entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.createQuery("select tu from HibernateCars tu order by tu.id", HibernateCars.class).getResultList();
        //return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public HibernateCars save(HibernateCars entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateCars.class, newID);
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateCars.class, entity.getId());
    }


    @Override
    public HibernateCars updateOne(HibernateCars entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateCars.class, entity.getId());
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateCars.class, entity.getId());
    }
}

