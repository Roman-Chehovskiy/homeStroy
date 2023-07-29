package com.string.HomeBuild.controllers;

import com.string.HomeBuild.entity.estimates.Estimate;
import com.string.HomeBuild.entity.estimates.Image;
import com.string.HomeBuild.entity.price.Construction;
import com.string.HomeBuild.service.ConstructionService;
import com.string.HomeBuild.service.EstimateConstructionService;
import com.string.HomeBuild.service.EstimateService;
import com.string.HomeBuild.utils.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ConstructionService constructionService;
    @Autowired
    private EstimateService estimateService;
    @Autowired
    private EstimateConstructionService estimateConstructionService;


    @RequestMapping("/allEstimates")
    public String showAllEstimates(Model model) {

        List<Estimate> estimates = estimateService.getAllEstimates();
        model.addAttribute("estimates", estimates);

        return "show-all-estimates";
    }

    @RequestMapping("/newEstimate")
    public String createNewEstimate(Model model) {

        Estimate estimate = new Estimate();
        estimate = DtoUtil.fillNewEstimate(estimate, constructionService);

        model.addAttribute("estimate", estimate);


        return "new-estimates";
    }

    @RequestMapping("/previewEstimate")
//    @ResponseBody
    public String previewEstimate(@ModelAttribute("estimate") Estimate estimate,
                                  BindingResult bindingResult, HttpServletRequest request, Model model,
                                  @RequestParam("file") MultipartFile[] multipartFiles) throws IOException {

        List<Image> imageList = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            Image image = new Image();
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            imageList.add(image);
        }


        if (bindingResult.hasErrors()) {

            estimate = DtoUtil.fillNewEstimate(estimate, constructionService);
            model.addAttribute("estimate", estimate);
            return "new-estimates";
        } else {

            if (estimate.getId() == 0) {

                estimate = DtoUtil.compileNewEstimate(estimate, request, constructionService);

            } else {

                estimate = DtoUtil.compileExistingEstimate(estimate, estimateService, constructionService, request, estimateConstructionService);

            }
            model.addAttribute("estimateResult", estimate);
            return "preview-estimate";
        }
    }

    @RequestMapping("saveEstimate")
    public String saveEstimate(@ModelAttribute("estimateResult") Estimate estimate
            , BindingResult bindingResult
            , HttpServletRequest request, Model model) {

        if (bindingResult.hasErrors()) {

            estimate = DtoUtil.compileNewEstimate(estimate, request, constructionService);
            model.addAttribute("estimateResult", estimate);

            return "preview-estimate";
        } else {
            if (estimate.getId() == 0) {

                estimate = DtoUtil.compileNewEstimate(estimate, request, constructionService);
                estimate = DtoUtil.editForSave(estimate);
                estimateService.saveEstimate(estimate);
            } else {
                estimate = DtoUtil.compileExistingEstimate(estimate, estimateService, constructionService,
                        request, estimateConstructionService);
                estimateService.updateEstimate(estimate, request);
            }
            return "redirect:/allEstimates";

        }
    }

    @RequestMapping("/deleteEstimate")
    public String deleteEstimate(@RequestParam("idEstimate") int id) {

        estimateService.deleteEstimate(id);

        return "redirect:/allEstimates";
    }

    @RequestMapping("/changeNewEstimate")
    public String changeNewEstimate(HttpServletRequest request, @ModelAttribute("estimateResult") Estimate estimate, Model model) {

        Estimate estimateResult = new Estimate();
        //заролняем Estimate введенными в форму данными
        if (estimate.getId() == 0) {
            estimate = DtoUtil.compileNewEstimate(estimate, request, constructionService);
        } else {
            estimate = DtoUtil.compileExistingEstimate(estimate, estimateService, constructionService, request, estimateConstructionService);

        }
        //создаём пустой Estimate, так как полученный Estimate из запроса не содержит не заполненных полей
        estimateResult = DtoUtil.fillNewEstimate(estimateResult, constructionService);
        //заполняем новый Estimate полученными данными и отправляем его на редактирование
        estimateResult = DtoUtil.fillEstimate(estimate, estimateResult);

        model.addAttribute("estimate", estimateResult);

        return "new-estimates";
    }

    @RequestMapping("/showEstimate")
    public String showEstimate(@RequestParam("idEstimate") int id, Model model) {

        Estimate estimate = estimateService.findEstimateById(id);

        model.addAttribute("estimate", estimate);


        return "show-estimate";
    }

    @RequestMapping("/changeEstimate")
    public String changeEstimate(@ModelAttribute("estimate") Estimate estimate, Model model) {

        estimate = estimateService.findEstimateById(estimate.getId());
        Estimate estimateResult = new Estimate();
        estimateResult = DtoUtil.fillNewEstimate(estimateResult, constructionService);
        estimateResult = DtoUtil.fillEstimate(estimate, estimateResult);

        model.addAttribute("estimate", estimateResult);

        return "new-estimates";
    }

    @RequestMapping("/allConstruction")
    public String showAllConstructions(Model model) {
        List<Construction> constructionList = constructionService.getAllConstructions();
        model.addAttribute("allConstructions", constructionList);
        return "/show-all-constructions";
    }


}
