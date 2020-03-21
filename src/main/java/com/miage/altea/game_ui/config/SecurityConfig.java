package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.pokemonTypes.bo.TrainerType;
import com.miage.altea.game_ui.service.TrainerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TrainerTypeService trainerTypeService;

    public void setTrainerTypeService(TrainerTypeService trainerTypeService) {
        this.trainerTypeService = trainerTypeService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (String username) -> {
            TrainerType trainer = this.trainerTypeService.getTrainerType(username);
            if (trainer == null) {
                throw new BadCredentialsException("No such user");
            }
            return new TrainerPrincipal(trainer);
        };
    }
}
