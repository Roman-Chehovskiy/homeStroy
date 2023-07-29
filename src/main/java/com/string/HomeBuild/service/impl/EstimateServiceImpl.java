package com.string.HomeBuild.service.impl;

import com.string.HomeBuild.entity.estimates.Estimate;
import com.string.HomeBuild.entity.estimates.EstimateConstruction;
import com.string.HomeBuild.reposinory.EstimateJPA;
import com.string.HomeBuild.reposinory.EstimateRepository;
import com.string.HomeBuild.service.ConstructionService;
import com.string.HomeBuild.service.EstimateConstructionService;
import com.string.HomeBuild.service.EstimateService;
import com.string.HomeBuild.utils.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;
    @Autowired
    private EstimateJPA estimateJPA;

    @Autowired
    private ConstructionService constructionService;
    @Autowired
    private EstimateConstructionService estimateConstructionService;

    @Override
    @Transactional
    public void saveEstimate(Estimate estimate) {


             estimateRepository.saveEstimate(estimate);
    }

    @Override
    @Transactional
    public void updateEstimate(Estimate estimate, HttpServletRequest request) {

        Estimate estimateOld = findEstimateById(estimate.getId());
        estimateRepository.deleteEstimate(estimateOld.getId());

       estimateOld = DtoUtil.editForUpdate(estimate, estimateOld);

        Estimate estimate1 = new Estimate();
        estimate1.setName(estimate.getName());
        estimate1.setMoney(estimate.getMoney());
        for(EstimateConstruction estimateConstruction : estimateOld.getConstructionList()) {
            estimate1.addConstructionToEstimate(estimateConstruction);
        }

        estimateRepository.saveEstimate(estimate1);

    }

    @Override
    @Transactional
    public List<Estimate> getAllEstimates() {
        return estimateRepository.getAllEstimates();
    }

    @Override
    @Transactional
    public void deleteEstimate(int id) {
        estimateRepository.deleteEstimate(id);
    }

    @Override
    @Transactional
    public Estimate findEstimateById(int id) {
        Estimate estimate = estimateRepository.getEstimateById(id);
        return estimate;
    }
}
