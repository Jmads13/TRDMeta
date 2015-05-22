/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import org.openide.util.Lookup;

/**
 *
 * @author Pasoa
 */
public class Player implements IContentService{

    Object o;
    
    @Override
    public void add(Object o) {
        this.o = o;
        Link<Entity> pl = context(o).add(Entity.class, createPlayer());
    }

    @Override
    public void remove() {
        for(Entity e : context(o).all(Entity.class)){
            if(context(e).one(EntityType.class) == EntityType.PLAYER){
                e.setDestroyed(true);
            }
        }
    }
    
    public Entity createPlayer() {
        Entity player = new Entity();
        
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/RedTile.png").toExternalForm();

        context(player).add(EntityType.class, PLAYER);
        context(player).add(Scale.class, new Scale(0.0f, 0.0f));
        context(player).add(ImageAsset.class, new ImageAsset(url));
        context(player).add(Position.class, new Position(0, 0));
        return player;
    }
    
}
