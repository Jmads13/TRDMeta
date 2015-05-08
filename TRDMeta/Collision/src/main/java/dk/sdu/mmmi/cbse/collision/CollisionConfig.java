/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author SoA
 */
@Configuration
public class CollisionConfig {
    
    @Bean
    @Scope(value = "prototype")
    public IUpdateService createCollisionDetectionService() {
        return new CollisionDetection();
    }
    
}
