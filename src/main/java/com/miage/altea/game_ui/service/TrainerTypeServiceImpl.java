package com.miage.altea.game_ui.service;

import com.miage.altea.game_ui.pokemonTypes.bo.TrainerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TrainerTypeServiceImpl implements TrainerTypeService {
    private RestTemplate restTemplate;
    private String TrainerServiceUrl;

    public List<TrainerType> listTrainers() {
        HttpHeaders entete = new HttpHeaders();
        entete.setContentLanguage(LocaleContextHolder.getLocale());
        TrainerType[] lpoke = this.restTemplate.getForObject(TrainerServiceUrl+"/trainers/", TrainerType[].class);
        return List.of(lpoke);
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