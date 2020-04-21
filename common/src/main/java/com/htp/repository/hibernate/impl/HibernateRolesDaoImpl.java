package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateRoles;
import com.htp.repository.hibernate.HibernateRolesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateRolesDao")
public class HibernateRolesDaoImpl implements HibernateRolesDao {

    private final EntityManager entityManager;

    @Override
    public List<HibernateRoles> findAll() {
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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateRoles.class, entity.getId());
    }

    @Override
    public HibernateRoles updateOne(HibernateRoles entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateRoles.class, entity.getId());
    }
}
