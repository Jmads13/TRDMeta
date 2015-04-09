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
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.PLACING;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
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
            //System.out.println(p.x + " : " + p.y);
            
//            if(ctx.one(BehaviorType.class).equals(PLACING)){
//                ImageAsset ia = ctx.one(ImageAsset.class);
//                ia.setImageAsset("images/RedTile.png");
//                //Check imod map entities om der kan bygges, else set to greenTile.png
//            }
        }
    }
}
