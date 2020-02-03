package com.miage.altea.game_ui.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    RestTemplate restTemplate;
    String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        PokemonType[] lpoke = this.restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/", PokemonType[].class);
        return List.of(lpoke);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}