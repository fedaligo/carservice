package com.htp.repository.hibernate.impl;

import com.htp.repository.hibernate.HibernateCarsDao;
import com.htp.domain.hibernate.HibernateCars;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Qualifier("hibernateCarsDao")
public class HibernateCarsDaoImpl implements HibernateCarsDao {

    //@Autowired
    private final EntityManager entityManager;

    @Override
    public List<HibernateCars> findAll() {
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
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public HibernateCars save(HibernateCars entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateCars.class, entity.getId());
    }

    @Override
    public HibernateCars updateOne(HibernateCars entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        return entityManager.find(HibernateCars.class, entity.getId());
    }
}

