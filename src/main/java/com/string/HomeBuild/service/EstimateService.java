package com.string.HomeBuild.service;

import com.string.HomeBuild.entity.estimates.Estimate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EstimateService {

    void saveEstimate(Estimate estimate);
    List<Estimate> getAllEstimates();
    void deleteEstimate(int id);
    Estimate findEstimateById(int id);

    void updateEstimate(Estimate estimate, HttpServletRequest  request);

}
