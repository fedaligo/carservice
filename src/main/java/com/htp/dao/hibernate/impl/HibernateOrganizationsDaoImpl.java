package com.htp.dao.hibernate.impl;

import com.htp.dao.hibernate.HibernateOrganizationsDao;
import com.htp.dao.hibernate.HibernateRolesDao;
import com.htp.entity.hibernate.HibernateOrganizations;
import com.htp.entity.hibernate.HibernateRoles;
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
@Qualifier("hibernateOrganizationsDao")
public class HibernateOrganizationsDaoImpl implements HibernateOrganizationsDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(HibernateOrganizations entity) {

    }

    @Override
    public List<HibernateOrganizations> findAll() {
        /*try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateUsers tu", HibernateUsers.class).list();
        }*/
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateOrganizations tu order by tu.id", HibernateOrganizations.class).getResultList();
    }

    @Override
    public HibernateOrganizations findById(Long id) {
        return entityManager.find(HibernateOrganizations.class, id);
    }

    @Override
    public List<HibernateOrganizations> update(HibernateOrganizations entity) {
        return Collections.emptyList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public HibernateOrganizations save(HibernateOrganizations entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newID = (Long)session.save(entity);
            transaction.commit();
            return session.find(HibernateOrganizations.class, newID);
        }
    }

    @Override
    public HibernateOrganizations updateOne(HibernateOrganizations entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HibernateOrganizations.class, entity.getId());
        }
    }
}
