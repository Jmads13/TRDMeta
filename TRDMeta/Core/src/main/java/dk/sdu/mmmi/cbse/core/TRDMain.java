/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.core;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
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
    Object world;
    
    public TRDMain(int updateRate) {
        super(updateRate);
    }

    @Override
    public void init() {
        rootLayer = graphics().rootLayer();
        EntityArr = new ArrayList<>();
        world = new Object();
        System.out.println("ContentServices located: " +Lookup.getDefault().lookupAll(IContentService.class).size());
        System.out.println("UpdateableServices located: " +Lookup.getDefault().lookupAll(IUpdateService.class).size());
        
    }
    
    @Override
    public void update(int delta) {
        for(IUpdateService service : Lookup.getDefault().lookupAll(IUpdateService.class)){
            for (Entity e : context(world).all(Entity.class)) {
            
            }
        }
    }
    
    @Override
    public void paint(float alpha){ 
        for (Entity e : context(world).all(Entity.class)) {
            
        }
    }
    
}
