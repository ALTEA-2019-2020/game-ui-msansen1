package com.miage.altea.game_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GameUI {

    public static void main(String... args){
        SpringApplication.run(GameUI.class, args);
    }

}
