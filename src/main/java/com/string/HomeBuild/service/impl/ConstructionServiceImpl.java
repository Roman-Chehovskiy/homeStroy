package com.string.HomeBuild.service.impl;

import com.string.HomeBuild.entity.price.Construction;
import com.string.HomeBuild.reposinory.ConstructionRepository;
import com.string.HomeBuild.service.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ConstructionServiceImpl implements ConstructionService {

    @Autowired
    private ConstructionRepository constructionRepository;

    @Override
    @Transactional
    public List<Construction> getAllConstructions() {
        return constructionRepository.getAllConstruction();
    }

    @Override
    @Transactional
    public void deleteConstructionById(long id) {
        constructionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Construction getConstructionById(long id) {
        Construction construction = constructionRepository.getConstructionById(id);
        return construction;
    }

    @Override
    @Transactional
    public void editConstruction(Construction construction) {
        constructionRepository.editConstruction(construction);
    }

    @Override
    @Transactional
    public void addNewConstruction(Construction construction) {
        constructionRepository.saveConstruction(construction);
    }
}
