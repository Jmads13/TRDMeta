/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.LevelType;
import dk.sdu.mmmi.cbse.common.services.IFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SoA
 */
public class EnemyFactory implements IFactory{ 
    
    List<Entity> enemyArr = new ArrayList();



    
    @Override
    public List<Entity> createFromFactory(LevelType et) {
        System.out.println("createFromFactory method");
        enemyArr.clear();
        switch(et){
            case LEVEL_1:
                enemyArr.add(new Enemy());
                System.out.println("Enemies in array: "+enemyArr.size());
                break;
            default:
                break;
        }
        return enemyArr;
    }
}
