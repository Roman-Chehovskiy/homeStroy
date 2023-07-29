package com.string.HomeBuild.reposinory.impl;

import com.string.HomeBuild.entity.estimates.Estimate;
import com.string.HomeBuild.reposinory.EstimateRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EstimateRepositoryImpl implements EstimateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEstimate(Estimate estimate) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(estimate);
    }

    @Override
    public List<Estimate> getAllEstimates() {

        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Estimate", Estimate.class);
        List<Estimate> estimates = query.getResultList();

        return estimates;
    }

    @Override
    public void deleteEstimate(int id) {

//        Session session = entityManager.unwrap(Session.class);
//        Query query = session.createQuery("delete from Estimate where id =: estimateId").setParameter("estimateId", id);
//        query.executeUpdate();
        Session session = entityManager.unwrap(Session.class);
        Estimate estimate = session.get(Estimate.class, id);
        session.delete(estimate);

    }

    @Override
    public Estimate getEstimateById(int id) {

        Session session = entityManager.unwrap(Session.class);
        Estimate estimate = session.get(Estimate.class, id);
//        session.evict(estimate);
        return estimate;
    }

    @Override
    public void updateEstimate(Estimate estimate) {
//
//        int id = estimate.getId();
//        String name = estimate.getName();
//        double money = estimate.getMoney();

        Session session = entityManager.unwrap(Session.class);
        session.clear();
        session.merge(estimate);
//        Query query = session.createQuery("update Estimate set name=:name, money=:money where id=:id")
//                .setParameter("name", name)
//                .setParameter("money", money)
//                .setParameter("id", id);
//        query.executeUpdate();

    }
}
