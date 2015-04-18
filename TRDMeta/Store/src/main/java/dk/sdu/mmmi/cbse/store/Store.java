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
        

        Link<Entity> bg = context(o).add(Entity.class, backGround(new Entity()));
        entities.add(bg);
        
        Link<Entity> topRight = context(o).add(Entity.class, topRight(new Entity()));
        entities.add(topRight);
        
        Link<Entity> topLeft = context(o).add(Entity.class, topLeft(new Entity()));
        entities.add(topLeft);
        
        Link<Entity> derpHerp = context(o).add(Entity.class, herpDerp(new Entity()));
        entities.add(derpHerp);
        
        Link<Entity> tankIcon = context(o).add(Entity.class, tankIcon(new Entity()));
        entities.add(tankIcon);
            
        Link<Entity> gasIcon = context(o).add(Entity.class, gasChamberIcon(new Entity()));
        entities.add(gasIcon);
        
    }

    @Override
    public void remove() {
        entities.dispose();
    }
    
    private Entity backGround(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/StoreBackground.png"));
        context(e).add(Position.class, new Position(320, 384+48));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity topRight(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/TopRight.png"));
        context(e).add(Position.class, new Position(550+42, 384+30));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity topLeft(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/TopLeft.png"));
        context(e).add(Position.class, new Position(550+42-82, 384+30));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity tankIcon(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/NaziTankIcon.png"));
        context(e).add(Position.class, new Position(70+15, 384+60));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity gasChamberIcon(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/GasChamberIcon.png"));
        context(e).add(Position.class, new Position(85+140, 384+60));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }

    private Entity herpDerp(Entity e){
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset("images/store/DerpHerp.png"));
        context(e).add(Position.class, new Position(550+1, 384+30+44));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
}
