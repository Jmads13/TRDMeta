/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.tower;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.PLACING;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.TOWER;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;

/**
 *
 * @author Pasoa
 */
class TowerProcess implements IUpdateService {

    Position playerPos = new Position(0, 0);
    
    @Override
    public void update(Object o, Entity entity) {
        if(context(entity).one(EntityType.class) == EntityType.PLAYER){
            playerPos = context(entity).one(Position.class);
        }
        if(context(entity).one(EntityType.class).equals(TOWER)){
            if(context(entity).one(BehaviorType.class) == PLACING){
                System.out.println(playerPos.x +" : "+playerPos.y);
                Position towerPos = context(entity).one(Position.class);
                towerPos = playerPos;
            }
            
        }
    }
    
}
