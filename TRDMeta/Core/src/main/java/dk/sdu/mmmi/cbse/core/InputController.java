/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.core;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import playn.core.Pointer;

/**
 *
 * @author Pasoa
 */
public class InputController extends Pointer.Adapter{
    Position p;
    public InputController(Entity player){
        p = context(player).one(Position.class);

    }
    
    @Override
    public void onPointerStart(Pointer.Event event) {
        p.x = event.x();
        p.y = event.y();
    }

    @Override
    public void onPointerEnd(Pointer.Event event) {

    }

    @Override
    public void onPointerDrag(Pointer.Event event) {
        p.x = event.x();
        p.y = event.y();
    }

    @Override
    public void onPointerCancel(Pointer.Event event) {
    }
}