package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.hibernate.HibernateRolesDao;
import com.htp.domain.hibernate.HibernateRoles;
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
@Qualifier("hibernateRolesDao")
public class HibernateRolesDaoImpl implements HibernateRolesDao {

    /*@Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;*/

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(HibernateRoles entity) {

    }

    @Override
    public List<HibernateRoles> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateRoles tu order by tu.id", HibernateRoles.class).getResultList();
    }

    @Override
    public HibernateRoles findById(Long id) {
        return entityManager.find(HibernateRoles.class, id);
    }

    @Override
    public List<HibernateRoles> update(HibernateRoles entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.createQuery("select tu from HibernateRoles tu order by tu.id", HibernateRoles.class).getResultList();
        //return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public HibernateRoles save(HibernateRoles entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateRoles.class, newID);
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateRoles.class, entity.getId());
    }

    @Override
    public HibernateRoles updateOne(HibernateRoles entity) {
        /*try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateRoles.class, entity.getId());
        }*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateRoles.class, entity.getId());
    }

}
