package com.miage.altea.game_ui.pokemonTypes.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TrainerType {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private List<PokemonType> team;

}
