package com.string.HomeBuild.reposinory;

import com.string.HomeBuild.entity.price.Construction;

import java.util.List;

public interface ConstructionRepository {

    List<Construction> getAllConstruction();

    void deleteById(long id);

    Construction getConstructionById(long id);

    void editConstruction(Construction construction);

    void saveConstruction(Construction construction);
}
