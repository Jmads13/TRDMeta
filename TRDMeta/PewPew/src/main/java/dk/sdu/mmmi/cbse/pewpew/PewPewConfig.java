/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.pewpew;

import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Pasoa
 */
@Configuration
public class PewPewConfig {
    
    @Bean
    @Scope(value = "prototype")
    public IUpdateService createShootingUpdateService() {
        return new Shooting();
    }
    
}
