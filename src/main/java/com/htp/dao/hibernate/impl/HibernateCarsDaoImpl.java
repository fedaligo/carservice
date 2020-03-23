package com.htp.dao.hibernate.impl;

import com.htp.dao.hibernate.HibernateCarsDao;
import com.htp.dao.hibernate.HibernateOrganizationsDao;
import com.htp.entity.hibernate.HibernateCars;
import com.htp.entity.hibernate.HibernateOrganizations;
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
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public HibernateCars save(HibernateCars entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateCars.class, newID);
        }
    }

    @Override
    public HibernateCars updateOne(HibernateCars entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateCars.class, entity.getId());
        }
    }
}

