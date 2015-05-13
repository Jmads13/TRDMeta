/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.core;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import dk.sdu.mmmi.cbse.common.data.types.EntitySubType;
import playn.core.Mouse;


/**
 *
 * @author Pasoa
 */
public class LayerListenerController extends Mouse.LayerAdapter{
    private static LayerListenerController instance;
    private static Entity player;
    private LayerListenerController(){
        
    }
    
    public static LayerListenerController getInstance(Entity player){
        if(instance != null){
            return instance;
        }
        LayerListenerController.player = player;
        return (instance = new LayerListenerController());
    }
    
    public Mouse.LayerAdapter setTankListener(Entity e){
        Mouse.LayerAdapter ma = new Mouse.LayerAdapter() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent event) {
                context(player).add(EntitySubType.class, EntitySubType.BUYING_NAZITANK);
                context(player).add(BehaviorType.class, BehaviorType.INIT);
            }
        };
        return ma;
    }
    
    public Mouse.LayerAdapter setChamberListener(Entity e){
        Mouse.LayerAdapter ma = new Mouse.LayerAdapter() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent event) {
                context(player).add(EntitySubType.class, EntitySubType.BUYING_GASCHAMBER);
                context(player).add(BehaviorType.class, BehaviorType.INIT);
            }
        };
        return ma;
    }
    
    public Mouse.LayerAdapter setTowerListener(final Entity e){
        Mouse.LayerAdapter ma = new Mouse.LayerAdapter() {
            @Override
            public void onMouseDown(Mouse.ButtonEvent event) {
                context(e).remove(BehaviorType.PLACING);
                context(e).add(BehaviorType.class, BehaviorType.SPAWNING);
            }
        };
        return ma;
    }
    
}
