package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.repository.hibernate.HibernateOrganizationsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateOrganizationsDao")
public class HibernateOrganizationsDaoImpl implements HibernateOrganizationsDao {

    //@Autowired
    private final EntityManager entityManager;

    @Override
    public List<HibernateOrganizations> findAll() {
        System.out.println(entityManager.toString());
        return entityManager.createQuery("select tu from HibernateOrganizations tu order by tu.id", HibernateOrganizations.class).getResultList();
    }

    @Override
    public HibernateOrganizations findById(Long id) {
        return entityManager.find(HibernateOrganizations.class, id);
    }

    @Override
    public List<HibernateOrganizations> update(HibernateOrganizations entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.createQuery("select tu from HibernateOrganizations tu order by tu.id", HibernateOrganizations.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public HibernateOrganizations save(HibernateOrganizations entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateOrganizations.class, entity.getId());
    }

    @Override
    public HibernateOrganizations updateOne(HibernateOrganizations entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateOrganizations.class, entity.getId());
    }
}
