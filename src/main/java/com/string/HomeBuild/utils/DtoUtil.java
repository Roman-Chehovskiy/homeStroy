package com.string.HomeBuild.utils;

import com.string.HomeBuild.entity.estimates.Estimate;
import com.string.HomeBuild.entity.estimates.EstimateConstruction;
import com.string.HomeBuild.entity.estimates.EstimateMaterial;
import com.string.HomeBuild.entity.price.Construction;
import com.string.HomeBuild.entity.price.Material;
import com.string.HomeBuild.service.ConstructionService;
import com.string.HomeBuild.service.EstimateConstructionService;
import com.string.HomeBuild.service.EstimateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DtoUtil {

    //получаем из HttpServletRequest заполненные данные по названию столбцов и заполняем ими Estimate объект

    public static Estimate compileNewEstimate(Estimate estimate, HttpServletRequest request,
                                              ConstructionService constructionService) {

        List<Construction> constructionList = constructionService.getAllConstructions();
        for (Construction construction : constructionList) {

            estimate = compileEstimate(estimate, request, construction.getId());
        }


        return estimate;
    }

    public static Estimate compileExistingEstimate(Estimate estimate, EstimateService estimateService
            , ConstructionService constructionService, HttpServletRequest request
            , EstimateConstructionService estimateConstructionService) {

//        Estimate oldEstimate = estimateService.findEstimateById(estimate.getId());

        Estimate estimateResult = new Estimate();
        //собираем введенные данные в estimate
        estimate = compileNewEstimate(estimate, request, constructionService);
        List<EstimateConstruction> constructionList = estimateConstructionService.getAllConstructionsByEstimateId(estimate.getId());

        for (int i = 0; i < constructionList.size(); i++) {
            estimateResult = compileEstimate(estimateResult, request, constructionList.get(i).getId());
        }

       estimate = fillEstimate(estimate, estimateResult);

        return estimate;
    }

    //Заполняем объект Estimate всеми необходимыми данными для расчетов из базы данных
    public static Estimate fillNewEstimate(Estimate estimate, ConstructionService constructionService) {

        List<Construction> constructionList = constructionService.getAllConstructions();

        for (Construction construction : constructionList) {
            EstimateConstruction estimateConstruction = new EstimateConstruction();


            for (Material material : construction.getMaterialsList()) {
                EstimateMaterial estimateMaterial = new EstimateMaterial();
                estimateMaterial.setId(material.getId());
                estimateMaterial.setName(material.getMaterial());
                estimateMaterial.setPrice(material.getPrice());
                estimateConstruction.addMaterialToConstruction(estimateMaterial);
            }

            estimateConstruction.setId(construction.getId());
            estimateConstruction.setName(construction.getConstruction());
            estimate.addConstructionToEstimate(estimateConstruction);

        }

        estimate.setMoney(0);

        return estimate;
    }

    //заполняем пустой Estimate
    public static Estimate fillEstimate(Estimate estimate, Estimate changeEstimate) {

        Estimate estimateResult = new Estimate();

        if (estimate.getConstructionList() == null) {
            estimateResult.setConstructionList(changeEstimate.getConstructionList());
        } else if (changeEstimate.getConstructionList() == null) {
            estimateResult.setConstructionList(estimate.getConstructionList());
        } else {

            boolean checkMaterial;
            boolean checkConstruction;

            for (EstimateConstruction estimateConstructionChange : changeEstimate.getConstructionList()) {
                checkConstruction = false;
                if (estimate.getConstructionList() != null) {

                    for (EstimateConstruction estimateConstruction : estimate.getConstructionList()) {

                        if (estimateConstructionChange.getName().equals(estimateConstruction.getName())) {
                            EstimateConstruction estimateConstructionResult = new EstimateConstruction();

                            for (EstimateMaterial estimateMaterialChange : estimateConstructionChange.getMaterialsList()) {
                                checkMaterial = false;

                                for (EstimateMaterial estimateMaterial : estimateConstruction.getMaterialsList()) {
                                    if (estimateMaterialChange.getName().equals(estimateMaterial.getName()) &&
                                            estimateMaterialChange.getPrice() == estimateMaterial.getPrice()) {
                                        estimateConstructionResult.addMaterialToConstruction(estimateMaterial);
                                        checkMaterial = true;
                                        break;
                                    }
                                }

                                if (!checkMaterial) {
                                    estimateConstructionResult.addMaterialToConstruction(estimateMaterialChange);
                                }
                            }

                            estimateConstructionResult.setId(estimateConstruction.getId());
                            estimateConstructionResult.setName(estimateConstruction.getName());
                            estimateResult.addConstructionToEstimate(estimateConstructionResult);
                            checkConstruction = true;
                            estimate.getConstructionList().remove(estimateConstruction);
                            break;
                        }
                    }
                }
                if (!checkConstruction) {
                    estimateResult.addConstructionToEstimate(estimateConstructionChange);
                }
            }
            if (estimate.getConstructionList().size() != 0) {
                estimateResult.getConstructionList().addAll(estimate.getConstructionList());
            }
        }

        estimateResult.setId(estimate.getId());
        estimateResult.setName(estimate.getName());
        estimateResult.setMoney(estimate.getMoney());
        estimate.setId(1);

        return estimateResult;
    }

    private static Estimate compileEstimate(Estimate estimate, HttpServletRequest request
            , long id) {
        // id конструкции в запросе совпадает с id конструкции в базе данных, поэтому на его основании формируем
        //параметры запроса данных из таблицы

        String idConstruction = String.valueOf(id);
        String idMaterial = id + "id";
        String materialNameId = id + "name";
        String materialPriceId = id + "price";
        String materialCountId = id + "count";

        //получаем массив данных из запроса для каждой конструкции

        String[] constructionArray = request.getParameterValues(idConstruction);
        String[] materialIdArray = request.getParameterValues(idMaterial);
        String[] materialNameArray = request.getParameterValues(materialNameId);
        String[] materialPriceArray = request.getParameterValues(materialPriceId);
        String[] materialCountArray = request.getParameterValues(materialCountId);

        if (constructionArray != null) {

            for (String s : constructionArray) {
                EstimateConstruction estimateConstruction = new EstimateConstruction();

                for (int n = 0; n < materialNameArray.length; n++) {

                    //проверяем если поля заролненны, то добавляем материал в смету

                    if (Double.parseDouble(materialCountArray[n]) != 0 && !materialCountArray[n].equals("")) {
                        EstimateMaterial estimateMaterial = new EstimateMaterial();
                        estimateMaterial.setId(Long.parseLong(materialIdArray[n]));
                        estimateMaterial.setName(materialNameArray[n]);
                        estimateMaterial.setPrice(Double.parseDouble(materialPriceArray[n]));
                        estimateMaterial.setCountMaterial(Double.parseDouble(materialCountArray[n]));
                        estimateMaterial.setMoney(estimateMaterial.getCountMaterial() * estimateMaterial.getPrice());
                        estimateConstruction.addMaterialToConstruction(estimateMaterial);
                    }
                }

                //если конструкция используется, то добавляем ее в смету
                if (estimateConstruction.getMaterialsList() != null) {
                    estimateConstruction.setName(s);
                    estimateConstruction.setId(Long.parseLong(idConstruction));

                    estimate.addConstructionToEstimate(estimateConstruction);
                }
            }
        }

        return estimate;
    }

    public static Estimate editForSave(Estimate estimate) {
        Estimate estimateResult = new Estimate();

        for (EstimateConstruction estimateConstruction : estimate.getConstructionList()) {
            EstimateConstruction estimateConstructionResult = new EstimateConstruction();

            for (EstimateMaterial estimateMaterial : estimateConstruction.getMaterialsList()) {
                estimateMaterial.setId(0);
                estimateConstructionResult.addMaterialToConstruction(estimateMaterial);
            }

            estimateConstructionResult.setName(estimateConstruction.getName());
            estimateResult.addConstructionToEstimate(estimateConstructionResult);
        }

        estimateResult.setMoney(estimate.getMoney());
        estimateResult.setName(estimate.getName());

        return estimateResult;
    }


    public static Estimate editForUpdate(Estimate estimate, Estimate estimateOld) {
        Estimate editEstimate = estimateOld;
        boolean checkConstruction;

        for(EstimateConstruction estimateConstructionNew : estimate.getConstructionList()) {
            checkConstruction = false;
            for(EstimateConstruction estimateConstructionOld : editEstimate.getConstructionList()) {
                if(estimateConstructionOld.getId() == estimateConstructionNew.getId()) {
                    estimateOld.getConstructionList().remove(estimateConstructionOld);
                    estimateOld.getConstructionList().add(estimateConstructionNew);
                    checkConstruction = true;
                    break;
                }
            }
            if (!checkConstruction) {
                estimateOld.addConstructionToEstimate(estimateConstructionNew);
            }
        }

        for(EstimateConstruction estimateConstructionOld : editEstimate.getConstructionList()) {
            checkConstruction = false;
            for(EstimateConstruction estimateConstructionNew : estimate.getConstructionList()) {
                if(estimateConstructionOld.getId() == estimateConstructionNew.getId()) {
                    checkConstruction = true;
                    break;
                }
            }
            if (!checkConstruction) {
                estimateOld.getConstructionList().remove(estimateConstructionOld);
            }
        }

        estimateOld.setName(estimate.getName());
        estimateOld.setMoney(estimate.getMoney());
        return estimateOld;
    }

    public static void getEditConstruction(HttpServletRequest request, Construction construction) {
//        Construction construction = new Construction();
        String[] materialName = request.getParameterValues("material");
        String[] materialPrice = request.getParameterValues("price");

        for(int i = 0; i < materialName.length; i++) {
            Material material = new Material();
            material.setMaterial(materialName[i]);
            material.setPrice(Double.parseDouble(materialPrice[i]));
            construction.addMaterialToConstruction(material);
        }

//        return construction;
    }
}


