package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.pokemonTypes.bo.TrainerType;
import com.miage.altea.game_ui.service.TrainerTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Test
    void securityConfig_shouldExtendWebSecurityConfigurerAdapter(){
        assertTrue(WebSecurityConfigurerAdapter.class.isAssignableFrom(SecurityConfig.class));
    }

    @Test
    void passwordEncoder_shouldBeBCryptPasswordEncoder() {
        var securityConfig = new SecurityConfig();
        var passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void userDetailsService_shouldUseTrainerService() {
        var securityConfig = new SecurityConfig();

        var trainerService = mock(TrainerTypeService.class);
        var trainer = new TrainerType();
        trainer.setName("Garry");
        trainer.setPassword("secret");
        when(trainerService.getTrainerType("Garry")).thenReturn(trainer);

        securityConfig.setTrainerTypeService(trainerService);

        var userDetailsService = securityConfig.userDetailsService();

        var garry = userDetailsService.loadUserByUsername("Garry");

        // mock should be called
        verify(trainerService).getTrainerType("Garry");

        assertNotNull(garry);
        assertEquals("Garry", garry.getUsername());
        assertEquals("secret", garry.getPassword());
        assertTrue(garry.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void userDetailsService_shouldThrowABadCredentialsException_whenUserDoesntExists() {
        var securityConfig = new SecurityConfig();

        // the mock returns null
        var trainerService = mock(TrainerTypeService.class);
        securityConfig.setTrainerTypeService(trainerService);

        var userDetailsService = securityConfig.userDetailsService();

        var exception = assertThrows(BadCredentialsException.class, () -> userDetailsService.loadUserByUsername("Garry"));
        assertEquals("No such user", exception.getMessage());

        // mock should be called
        verify(trainerService).getTrainerType("Garry");
    }

}
