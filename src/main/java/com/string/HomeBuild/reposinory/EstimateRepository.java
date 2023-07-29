package com.string.HomeBuild.reposinory;

import com.string.HomeBuild.entity.estimates.Estimate;

import java.util.List;

public interface EstimateRepository {

    void saveEstimate(Estimate estimate);

    void updateEstimate(Estimate estimate);

    List<Estimate> getAllEstimates();

    void deleteEstimate(int id);

    Estimate getEstimateById(int id);

}
