package com.string.HomeBuild.service;

import com.string.HomeBuild.entity.price.Construction;

import java.util.List;

public interface ConstructionService {

    List<Construction> getAllConstructions();
    void deleteConstructionById(long id);

    Construction getConstructionById(long id);

    void editConstruction(Construction construction);

    void addNewConstruction(Construction construction);
}
