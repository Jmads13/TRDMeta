/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.movement;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;


/**
 *
 * @author SoA
 */
public class PathFinding implements IUpdateService{

    @Override
    public void update(Object o, Entity entity) {
        /**
         * //TODO
         * Skal omskrives til ny service som injecter, world/context object, Entity entity, og "mappet"-> 
         * array af tiles/imageAssets/Eller hvad end vi nu laver af common.data class 
         * Meta.inf skal skrives
         * Hvis map komponent ligner up med lignende fields, som fra http://goo.gl/e4VyaB
         * 
         * Kan største delen af koden fra cokeandcode.com bruges som skabelon
         * 
         * Omsæt det injectet tile array, 
         * Opret Open/Closed listerne
         * Find Path();
         * Inject path into Enemy
         */
    }

}
