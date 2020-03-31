package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.service.TrainerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    TrainerTypeService trainerTypeService;

    @GetMapping("/")
    public ModelAndView index(Principal principal){
        var model = new ModelAndView();
        model.setViewName("index");
        model.addObject("trainerTypes", trainerTypeService.listTrainersTypes(principal.getName()));
        model.addObject("name", principal.getName());
        return model;
    }

    @PostMapping("/registerTrainer")
    public ModelAndView registerNewTrainer(String trainerName){
        var model = new ModelAndView();
        model.setViewName("register");
        model.addObject("name", trainerName);
        return model;
    }

}