package com.string.HomeBuild.reposinory;

import com.string.HomeBuild.entity.estimates.EstimateConstruction;

import java.util.List;

public interface EstimateConstructionRepository {

    List<EstimateConstruction> getAllConstructionsByEstimateId(int id);

}
