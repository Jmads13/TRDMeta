/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.PLACING;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.services.IContentService;

/**
 *
 * @author Pasoa
 */
public class Player implements IContentService{
    
    DisposableList entities = new DisposableList();

    @Override
    public void add(Object o) {
        Link<Entity> pl = context(o).add(Entity.class, createPlayer());
        entities.add(pl);
    }

    @Override
    public void remove() {
        entities.dispose();
    }
    
    public Entity createPlayer() {
        Entity player = new Entity();

        context(player).add(EntityType.class, PLAYER);
        context(player).add(Scale.class, new Scale(0.0f, 0.0f));
        context(player).add(ImageAsset.class, new ImageAsset("images/RedTile.png"));
        context(player).add(Position.class, new Position(0, 0));
        return player;
    }
    
}
