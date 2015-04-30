/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.tower;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Range;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
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
            if(context(entity).one(BehaviorType.class).equals(BehaviorType.SPAWNING)){
                entity.setDestroyed(true); //Destroy's drag and drop entity
                    Entity tower = new Entity();
                    context(tower).add(EntityType.class, TOWER);
                    context(tower).add(Range.class, new Range(200));
                    context(tower).add(BehaviorType.class, PLACING);
                    context(tower).add(Rotation.class, new Rotation());
                    context(tower).add(ImageAsset.class, new ImageAsset("images/Nazi_Tank.png"));
                    context(tower).add(Position.class, new Position(playerPos.x, playerPos.y));
                    context(tower).add(Scale.class, new Scale(1f,1f));
                    context(o).add(Entity.class, tower);
            }
                
        }
    }
    
}
