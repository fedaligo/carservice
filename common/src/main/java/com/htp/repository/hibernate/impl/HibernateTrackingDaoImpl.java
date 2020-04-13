package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.hibernate.HibernateTrackingDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateTrackingDao")
public class HibernateTrackingDaoImpl implements HibernateTrackingDao{

        //@Autowired
        private final EntityManager entityManager;

        @Override
        public List<HibernateTracking> findAll() {
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
        }

        @Override
        public void deleteById(Long id) {
            entityManager.remove(findById(id));
        }

        @Override
        public HibernateTracking save(HibernateTracking entity) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.find(HibernateTracking.class, entity.getId());
        }

        @Override
        public HibernateTracking updateOne(HibernateTracking entity) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entityManager.find(HibernateTracking.class, entity.getId());
        }

}
