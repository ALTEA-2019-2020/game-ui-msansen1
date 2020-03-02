package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.service.TrainerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerTypeController {

    TrainerTypeService TrainerTypeService;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        var model = new ModelAndView();
        model.setViewName("pokedex");
        model.addObject("TrainerTypes", TrainerTypeService.listTrainersTypes());
        return model;
    }

    @Autowired
    public void setTrainerTypeService(TrainerTypeService TrainerTypeService) {
        this.TrainerTypeService = TrainerTypeService;
    }
}
