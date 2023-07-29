package com.string.HomeBuild.service.impl;

import com.string.HomeBuild.entity.estimates.EstimateConstruction;
import com.string.HomeBuild.reposinory.EstimateConstructionRepository;
import com.string.HomeBuild.service.EstimateConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstimateConstructionServiceImpl implements EstimateConstructionService {

    @Autowired
    private EstimateConstructionRepository estimateConstructionRepository;

    @Override
    @Transactional
    public List<EstimateConstruction> getAllConstructionsByEstimateId(int id) {

        List<EstimateConstruction> estimateConstructionList = estimateConstructionRepository.getAllConstructionsByEstimateId(id);
        return estimateConstructionList;
    }
}
