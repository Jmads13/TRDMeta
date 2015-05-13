/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.core;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import playn.core.Mouse;

/**
 *
 * @author Pasoa
 * 
 * Adds a Position to the player Entity (Mouse coordinates)
 * 
 */
public class InputController extends Mouse.Adapter{
    Position p;
    public InputController(Entity player){
        p = context(player).one(Position.class);

    }
    
    @Override
    public void onMouseDown(Mouse.ButtonEvent event) {
    }


    @Override
    public void onMouseMove(Mouse.MotionEvent event) {
        p.x = event.x();
        p.y = event.y();
    }
}