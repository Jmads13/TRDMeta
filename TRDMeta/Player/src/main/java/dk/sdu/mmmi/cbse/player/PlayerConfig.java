/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 *
 * @author Pizzie
 */
@Configuration
public class PlayerConfig {

    @Bean
    @Scope(value = "prototype")
    public IUpdateService createPlayerUpdateService() {
        return new PlayerProcess();
    }

    @Bean
    @Scope(value = "prototype")
    public IContentService createPlayerContentService() {
        return new Player();
    }
}
