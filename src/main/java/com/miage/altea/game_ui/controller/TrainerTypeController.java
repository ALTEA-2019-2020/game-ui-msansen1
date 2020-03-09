package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.service.TrainerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerTypeController {

    TrainerTypeService trainerTypeService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        var model = new ModelAndView();
        model.setViewName("trainers");
        model.addObject("trainerTypes", trainerTypeService.listTrainersTypes());
        return model;
    }


    @GetMapping(value="/trainers/{name}")
    public ModelAndView trainerByName(@PathVariable String name) {
        var modelAndView = new ModelAndView("trainerSolo");
        modelAndView.addObject("trainerSolo", trainerTypeService.getTrainerType(name));
        return modelAndView;

    }

    @Autowired
    public void setTrainerTypeService(TrainerTypeService TrainerTypeService) {
        this.trainerTypeService = TrainerTypeService;
    }
}
