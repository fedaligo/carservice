package com.htp.dao.hibernate.impl;

import com.htp.dao.hibernate.HibernateTrackingDao;
import com.htp.dao.hibernate.HibernateUsersDao;
import com.htp.entity.hibernate.HibernateTracking;
import com.htp.entity.hibernate.HibernateUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("hibernateTrackingDao")
public class HibernateTrackingDaoImpl implements HibernateTrackingDao{

        @Autowired
        @Qualifier("sessionFactory")
        private SessionFactory sessionFactory;

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
            return Collections.emptyList();
        }

        @Override
        public void deleteById(Long id) {
            entityManager.remove(findById(id));
        }


        @Override
        public HibernateTracking save(HibernateTracking entity) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.getTransaction();
                transaction.begin();
                Long newID = (Long)session.save(entity);
                transaction.commit();
                return session.find(HibernateTracking.class, newID);
            }
        }

        @Override
        public HibernateTracking updateOne(HibernateTracking entity) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.getTransaction();
                transaction.begin();
                session.saveOrUpdate(entity);
                transaction.commit();
                return session.find(HibernateTracking.class, entity.getId());
            }
        }
}
