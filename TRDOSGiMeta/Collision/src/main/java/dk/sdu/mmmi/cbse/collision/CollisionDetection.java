/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collision;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SoA
 */
class CollisionDetection implements IUpdateService{

    public CollisionDetection() {
        
    }

    @Override
    public void update(Object o, Entity e) {
        List<Entity> entities = new ArrayList<Entity>(context(o).all(Entity.class));
        //Collections.reverse(entities);
        for(Entity target : entities){
            if(context(target).one(EntityType.class).equals(EntityType.ENEMY)
                    && context(target).one(BehaviorType.class) == BehaviorType.ASTAR){
                 if(context(e).one(EntityType.class).equals(EntityType.BULLET)){
                     if(checkCollision(e, target)){
                         target.setDestroyed(true); //Taking dmg instead of killing it
                     }
                 }
            }
        
    }
    
}

    private boolean checkCollision(Entity source, Entity target) {
        Position srcPos = context(source).one(Position.class);
        Position targetPos = context(target).one(Position.class);

        float dx = srcPos.x - targetPos.x;
        float dy = srcPos.y - targetPos.y;

        double dist = Math.sqrt((dx * dx) + (dy * dy));
        boolean withinRange = dist <= 30; //Monster radius
        System.out.println(dist);

        return withinRange;
    }
}
