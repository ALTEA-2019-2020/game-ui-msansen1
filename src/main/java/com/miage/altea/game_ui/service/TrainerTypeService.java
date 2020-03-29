package com.miage.altea.game_ui.service;

import com.miage.altea.game_ui.pokemonTypes.bo.TrainerType;

import java.util.List;

public interface TrainerTypeService {
    List<TrainerType> listTrainersTypes();

    List<TrainerType> listTrainersTypes(String principal);

    TrainerType getTrainerType(String name);
}