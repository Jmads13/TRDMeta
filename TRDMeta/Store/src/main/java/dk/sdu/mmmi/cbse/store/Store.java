package dk.sdu.mmmi.cbse.store;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Speed;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.SHOP;
import dk.sdu.mmmi.cbse.common.services.IContentService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pasoa
 */
public class Store implements IContentService{

    DisposableList entities = new DisposableList();
    
    

    @Override
    public void add(Object o) {
        
        Entity naziTank = new Entity();
        context(naziTank).add(EntityType.class, SHOP);
        context(naziTank).add(ImageAsset.class, new ImageAsset("images/Nazi_Tank.png"));
        context(naziTank).add(Position.class, new Position(600, 32));
        context(naziTank).add(Scale.class, new Scale(1.0f,1.0f));
        
        Entity gasChamber = new Entity();
        context(gasChamber).add(EntityType.class, SHOP);
        context(gasChamber).add(ImageAsset.class, new ImageAsset("images/GasChamber.png"));
        context(gasChamber).add(Position.class, new Position(570,175));
        context(gasChamber).add(Scale.class, new Scale(64/515f,64/515f));
        //context(gasChamber).add(Scale.class, new Scale(1.0f, 1.0f));
        
        Link<Entity> gc = context(o).add(Entity.class, gasChamber);
        entities.add(gc);
        Link<Entity> nt = context(o).add(Entity.class, naziTank);
        entities.add(nt);
    }

    @Override
    public void remove() {
        entities.dispose();
    }
    
    private static Entity createStoreTile(Position p){
        Entity e = new Entity();
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/Nazi_Tank.png"));
        context(e).add(Position.class, p);
        context(e).add(Scale.class, new Scale(128/945f,128/945f)); 
        
        
        return e;
    }
    
}
