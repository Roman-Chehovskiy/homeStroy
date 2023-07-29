package com.string.HomeBuild.reposinory.impl;

import com.string.HomeBuild.entity.price.Construction;
import com.string.HomeBuild.reposinory.ConstructionRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConstructionRepositoryImpl implements ConstructionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Construction> getAllConstruction() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Construction ", Construction.class);
        List<Construction> constructions = query.getResultList();
        return constructions;
    }

    @Override
    public void deleteById(long id) {
        Session session = entityManager.unwrap(Session.class);
        Construction construction = session.get(Construction.class, id);
        session.delete(construction);
    }

    @Override
    public Construction getConstructionById(long id) {
        Session session = entityManager.unwrap(Session.class);
        Construction construction = session.get(Construction.class, id);
        return construction;
    }

    @Override
    public void editConstruction(Construction construction) {
        deleteById(construction.getId());
        saveConstruction(construction);
    }

    @Override
    public void saveConstruction(Construction construction) {
        Session session = entityManager.unwrap(Session.class);
        session.save(construction);
    }
}
