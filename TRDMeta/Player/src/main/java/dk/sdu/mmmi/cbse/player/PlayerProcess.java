/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.decouplink.Context;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.PLACING;
import dk.sdu.mmmi.cbse.common.data.types.EntitySubType;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.TOWER;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;

/**
 *
 * @author Pasoa
 */
public class PlayerProcess implements IUpdateService{

    @Override
    public void update(Object o, Entity entity) {
        
        Context ctx = context(entity);
        if (ctx.one(EntityType.class).equals(PLAYER)){
            Position p = context(entity).one(Position.class);
            if(context(entity).one(EntitySubType.class) == EntitySubType.BUYING_NAZITANK){
                if(context(entity).one(BehaviorType.class) == BehaviorType.INIT){
                    context(entity).remove(BehaviorType.INIT);
                    
                    Entity tower = new Entity();
                    context(tower).add(EntityType.class, TOWER);
                    context(tower).add(BehaviorType.class, PLACING);
                    context(tower).add(ImageAsset.class, new ImageAsset("images/Nazi_Tank.png"));
                    context(tower).add(Position.class, p);
                    context(tower).add(Scale.class, new Scale(1f,1f));
                    context(o).add(Entity.class, tower);
                }
            }
        }
    }
}
