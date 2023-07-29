package com.string.HomeBuild.reposinory.impl;

import com.string.HomeBuild.entity.estimates.EstimateConstruction;
import com.string.HomeBuild.reposinory.EstimateConstructionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EstimateConstructionRepositoryImpl implements EstimateConstructionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EstimateConstruction> getAllConstructionsByEstimateId(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from EstimateConstruction where estimate_id =: estimateId").setParameter("estimateId", id);
        List<EstimateConstruction> constructionList = query.getResultList();
        return constructionList;
    }
}
