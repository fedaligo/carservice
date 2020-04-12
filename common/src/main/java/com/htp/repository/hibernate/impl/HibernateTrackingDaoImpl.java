package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateTasks;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.HibernateTrackingDao;
import com.htp.domain.hibernate.HibernateTracking;
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
@Qualifier("hibernateTrackingDao")
public class HibernateTrackingDaoImpl implements HibernateTrackingDao{

        /*@Autowired
        @Qualifier("sessionFactory")
        private SessionFactory sessionFactory;*/

        @Autowired
        private EntityManager entityManager;

        @Override
        public void create(HibernateTracking entity) {

        }

        @Override
        public List<HibernateTracking> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
            System.out.println(entityManager.toString());
            return entityManager.createQuery("select tu from HibernateTracking tu order by tu.id", HibernateTracking.class).getResultList();
        }

        @Override
        public HibernateTracking findById(Long id) {
            return entityManager.find(HibernateTracking.class, id);
        }

        @Override
        public List<HibernateTracking> update(HibernateTracking entity) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.createQuery("select tu from HibernateTracking tu order by tu.id", HibernateTracking.class).getResultList();
            //return Collections.emptyList();
        }

        @Override
        public void deleteById(Long id) {
            entityManager.remove(findById(id));
        }


        @Override
        public HibernateTracking save(HibernateTracking entity) {
            /*try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.getTransaction();
                transaction.begin();
                Long newID = (Long)session.save(entity);
                transaction.commit();
                return session.find(HibernateTracking.class, newID);
            }*/
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.find(HibernateTracking.class, entity.getId());
        }

        @Override
        public HibernateTracking updateOne(HibernateTracking entity) {
            /*try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.getTransaction();
                transaction.begin();
                session.saveOrUpdate(entity);
                transaction.commit();
                return session.find(HibernateTracking.class, entity.getId());
            }*/
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.find(HibernateTracking.class, entity.getId());
        }

}
