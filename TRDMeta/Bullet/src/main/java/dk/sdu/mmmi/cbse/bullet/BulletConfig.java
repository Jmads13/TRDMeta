/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.services.IContentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Pizzie
 */
@Configuration
public class BulletConfig {
        
    @Bean
    @Scope(value = "prototype")
    public IContentService createBulletContentService() {
        return new Bullet();
    }
}
