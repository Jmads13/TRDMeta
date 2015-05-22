package dk.sdu.mmmi.cbse.store;

import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.types.EntitySubType;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.SHOP;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import org.openide.util.Lookup;

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

    Object o;
    
    @Override
    public void add(Object o) {
        this.o = o;

        Link<Entity> bg = context(o).add(Entity.class, backGround(new Entity()));
        
        Link<Entity> topRight = context(o).add(Entity.class, topRight(new Entity()));
        
        Link<Entity> topLeft = context(o).add(Entity.class, topLeft(new Entity()));
        
        Link<Entity> derpHerp = context(o).add(Entity.class, herpDerp(new Entity()));
        
        Link<Entity> tankIcon = context(o).add(Entity.class, tankIcon(new Entity()));
            
        Link<Entity> gasIcon = context(o).add(Entity.class, gasChamberIcon(new Entity()));
    }

    @Override
    public void remove() {
        for(Entity e : context(o).all(Entity.class)){
            if(context(e).one(EntityType.class) == EntityType.SHOP){
                e.setDestroyed(true);
            }
        }
    }
    
    private Entity backGround(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/StoreBackground.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(Position.class, new Position(320, 384+48));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity topRight(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/TopRight.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(Position.class, new Position(550+42, 384+30));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity topLeft(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/TopLeft.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(Position.class, new Position(550+42-82, 384+30));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity tankIcon(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/NaziTankIcon.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(EntitySubType.class, EntitySubType.BUYING_NAZITANK);
        context(e).add(Position.class, new Position(70+15, 384+60));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
    
    private Entity gasChamberIcon(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/GasChamberIcon.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(Position.class, new Position(85+140, 384+60));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }

    private Entity herpDerp(Entity e){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/store/DerpHerp.png").toExternalForm();
        
        context(e).add(EntityType.class, SHOP);
        context(e).add(ImageAsset.class, new ImageAsset(url));
        context(e).add(Position.class, new Position(550+1, 384+30+44));
        context(e).add(Scale.class, new Scale(1.0f,1.0f));
        return e;
    }
}
