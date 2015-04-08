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
        int xpos, ypos;
        xpos = 540;
        ypos = 50;
        
        //TODO rewrite & perhaps rethink event handling
        for(int i = 0; i < 10; i++){
                Entity e = createStoreTile(new Position(xpos,ypos));
                Link<Entity> el = context(o).add(Entity.class, e);
                entities.add(el);
                if(i%2!=0){
                    ypos += 50;
                }
                if(i%2==0){
                    xpos += 50;
                }else{
                    xpos -=50;
                }
        }
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
        context(e).add(Scale.class, new Scale(10/189f,25/183f)); //945 x 366 to 50x50
        
        
        return e;
    }
    
}
