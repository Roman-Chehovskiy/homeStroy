package com.string.HomeBuild.service;

import com.string.HomeBuild.entity.estimates.EstimateConstruction;

import java.util.List;

public interface EstimateConstructionService {

    List<EstimateConstruction> getAllConstructionsByEstimateId(int id);

}
