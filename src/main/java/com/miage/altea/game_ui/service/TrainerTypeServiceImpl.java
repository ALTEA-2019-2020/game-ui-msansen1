package com.miage.altea.game_ui.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.bo.TrainerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TrainerTypeServiceImpl implements TrainerTypeService {
    private RestTemplate restTemplate;
    private String TrainerServiceUrl;

    @Autowired
    private PokemonTypeService pokemonTypeService;

    public List<TrainerType> listTrainersTypes() {
        HttpHeaders entete = new HttpHeaders();
        entete.setContentLanguage(LocaleContextHolder.getLocale());
        TrainerType[] lpoke = this.restTemplate.getForObject(TrainerServiceUrl+"/trainers/", TrainerType[].class);
        //boucle de parcour des pokemon pour chercher les details sur l'api pokemon

        for (TrainerType trainer : lpoke ) {

            List<PokemonType> pokelist = updateTeam(trainer);
            trainer.setTeam(pokelist);
        }
        /*
        var pokemonTypesSync = Arrays.stream(lpoke).map()
                .map(pokemonTypeService::getPokemonType).collect(Collector);
        */

        return List.of(lpoke);
    }

    public TrainerType getTrainerType(String name) {
        TrainerType trainer = this.restTemplate.getForObject(TrainerServiceUrl+"/trainers/{name}", TrainerType.class, name);
        //updating trainer team from pokemon api
        /*working
        for (PokemonType poke : trainer.getTeam() ) {
            PokemonType pokeFromApi = pokemonTypeService.getPokemonType(poke.getPokemonTypeId());
            poke.merge(pokeFromApi);
        }
        */

        List<PokemonType> pokelist = updateTeam(trainer);
        trainer.setTeam(pokelist);
        return trainer;

    }

    private List<PokemonType> updateTeam(TrainerType trainer) {
        return trainer.getTeam().stream().map(pokemonType ->{
                PokemonType popo = pokemonType.merge(pokemonTypeService.getPokemonType(pokemonType.getPokemonTypeId()));
                return popo;
            }).collect(Collectors.toList());
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainerType.service.url}")
    public void setTrainerTypeServiceUrl(String TrainerServiceUrl) {
        this.TrainerServiceUrl = TrainerServiceUrl;
    }
}