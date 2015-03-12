/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.core;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.LevelType;
import dk.sdu.mmmi.cbse.common.services.IDrawable;
import dk.sdu.mmmi.cbse.common.services.IFactory;
import dk.sdu.mmmi.cbse.common.services.IUpdateable;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import playn.core.Game;
import playn.core.Layer;
import static playn.core.PlayN.graphics;

/**
 *
 * @author SoA
 */
public class TRDMain extends Game.Default{

    Layer rootLayer;
    List<Entity> EntityArr;
    
    public TRDMain(int updateRate) {
        super(updateRate);
        System.out.println("Updateable services located: " +Lookup.getDefault().lookupAll(IUpdateable.class).size());
        System.out.println("Drawable services located: " +Lookup.getDefault().lookupAll(IDrawable.class).size());
        System.out.println("Factory services located: " +Lookup.getDefault().lookupAll(IFactory.class).size());
    }

    @Override
    public void init() {
        rootLayer = graphics().rootLayer();
        EntityArr = new ArrayList<>();
        LevelOne();
        
    }
    
    @Override
    public void update(int delta) {
         for(IUpdateable service : Lookup.getDefault().lookupAll(IUpdateable.class)){
             for(Entity e : EntityArr){
                service.update(delta,e);
             }
         }
    }
    
    @Override
    public void paint(float alpha){ 
        for(IDrawable service : Lookup.getDefault().lookupAll(IDrawable.class)){
            for(Entity e : EntityArr){
                service.paint(alpha,e);
            }
             
        }
    }
    
    public void LevelOne(){ //Burde laves mere elegant
        for(IFactory factory : Lookup.getDefault().lookupAll(IFactory.class)){
            System.out.println("level one method");
            EntityArr = factory.createFromFactory(LevelType.LEVEL_1);
        }
    }
}
