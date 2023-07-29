package com.string.HomeBuild.controllers;

import com.string.HomeBuild.entity.price.Construction;
import com.string.HomeBuild.service.ConstructionService;
import com.string.HomeBuild.utils.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ConstructionService constructionService;

//    @RequestMapping("info")
//    public String showAllInfo(Model model) {
//
//        List<Construction> constructions = constructionService.getAllConstructions();
//        model.addAttribute("constructions", constructions);
//
//        return "show-estimate";
//    }

    @RequestMapping("/deleteConstruction")
    public String deleteConstruction(Model model, @RequestParam("idConstruction") Long id) {
        constructionService.deleteConstructionById(id);
        List<Construction> constructions = constructionService.getAllConstructions();
        model.addAttribute("allConstruction", constructions);

        return "redirect:/allConstruction";
    }

    @RequestMapping("/editConstruction")
    public String editConstruction(Model model, @RequestParam("idConstruction") long id) {
        Construction construction = constructionService.getConstructionById(id);
        model.addAttribute("construction", construction);

        return "edit-construction";
    }

    @RequestMapping("/saveConstruction")
    public String saveConstruction(@ModelAttribute("construction") Construction construction, Model model
            , HttpServletRequest request) {

        DtoUtil.getEditConstruction(request, construction);
        if (construction.getId() != 0) {
            constructionService.editConstruction(construction);
        } else {
            constructionService.addNewConstruction(construction);
        }

        List<Construction> constructionList = constructionService.getAllConstructions();
        model.addAttribute("construction", constructionList);

        return "redirect:/allConstruction";
    }

    @RequestMapping("/newConstruction")
    public String addNewConstruction(Model model) {

        Construction construction = new Construction();
        model.addAttribute("construction", construction);

        return "new-construction";
    }

}
