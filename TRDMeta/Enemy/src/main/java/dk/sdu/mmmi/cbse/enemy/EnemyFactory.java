/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.enemy;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.ENEMY;


/**
 *
 * @author SoA
 */
public class EnemyFactory{ 
    
    
    public static Entity createEnemy(){
        Entity enemy = new Entity();
        
        //Add stuff to enemy (Via common data)
        context(enemy).add(EntityType.class, ENEMY);
        context(enemy).add(ImageAsset.class, new ImageAsset("images/DK.png"));
        
        //Finally return to start();
        return enemy;
    }

    

}
