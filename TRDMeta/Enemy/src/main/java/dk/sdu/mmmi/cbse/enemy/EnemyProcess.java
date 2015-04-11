/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.enemy;

import com.decouplink.Context;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.SPAWNING;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;

/**
 *
 * @author SoA
 */
public class EnemyProcess implements IUpdateService {

    @Override
    public void update(Object o, Entity entity) {
        
        Context enemyCtx = context(entity);
        if (enemyCtx.one(EntityType.class).equals(ENEMY)) {
            if(enemyCtx.one(BehaviorType.class).equals(SPAWNING)){
                //start moving
                BehaviorType bt = enemyCtx.one(BehaviorType.class);
                bt = BehaviorType.ASTAR;
            }
                Position pos = enemyCtx.one(Position.class);
                Velocity velocity = enemyCtx.one(Velocity.class);

                pos.x += velocity.vectorX;
                pos.y += velocity.vectorY;
            
        }
    }
    
}
