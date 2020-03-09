package com.miage.altea.game_ui.pokemonTypes.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonType {

    private int id;
    private int pokemonTypeId;
    private int level;
    private int baseExperience;
    private int height;
    private String name;
    private Sprites sprites;
    private Stats stats;
    private int weight;
    private List<String> types;

    public PokemonType merge(PokemonType pokemon) {
        this.id = pokemon.id;
        this.baseExperience = pokemon.baseExperience;
        this.height = pokemon.height;
        this.name = pokemon.name;
        this.sprites = pokemon.sprites;
        this.stats = pokemon.stats;
        this.weight = pokemon.weight;
        this.types = pokemon.types;
        return this;
    }
}
