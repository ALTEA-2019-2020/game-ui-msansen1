package com.miage.altea.game_ui.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {
        HttpHeaders entete = new HttpHeaders();
        entete.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
        HttpEntity entity = new HttpEntity(entete);
        ResponseEntity<PokemonType[]> lpoke = restTemplate.exchange(pokemonServiceUrl+"/pokemon-types/", HttpMethod.GET, entity, PokemonType[].class);
        return List.of(lpoke.getBody());
    }

    @Override
    @Cacheable("pokemon-types")
    public PokemonType getPokemonType(int id) {
        return this.restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/{id}", PokemonType.class, id);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}