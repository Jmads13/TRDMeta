/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.pewpew;

import com.decouplink.DisposableList;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Radius;
import dk.sdu.mmmi.cbse.common.data.Range;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pasoa
 */
class Shooting implements IUpdateService {

    DisposableList disposables = new DisposableList();
    
    public Shooting() {
    }

    @Override
    public void update(Object o, Entity e) {
        List<Entity> entities = new ArrayList<Entity>(context(o).all(Entity.class));
        disposables.dispose();  
        
        for(Entity target : entities){
            if(context(target).one(EntityType.class).equals(EntityType.ENEMY)){
                if(context(e).one(EntityType.class).equals(EntityType.TOWER)){
                    if(testRange(e, target)){
                        Rotation rotation = context(e).one(Rotation.class);
                        rotation.angle = calcAngle(e, target);
                    }
                }
            }
        }
    }
    
    private boolean testRange(Entity source, Entity target) {

        Position srcPos = context(source).one(Position.class);
        Position targetPos = context(target).one(Position.class);

        float dx = srcPos.x - targetPos.x;
        float dy = srcPos.y - targetPos.y;

        double dist = Math.sqrt((dx * dx) + (dy * dy));
        boolean withinRange = dist <= 328; //Rewrite to range of tower

        return withinRange;
    }
    
    private float calcAngle(Entity e, Entity target) {
        Position pos1 = context(e).one(Position.class);
        Position pos2 = context(target).one(Position.class);
        
        Point p1 = new Point((int)pos1.x, (int)pos1.y);
        Point p2 = new Point((int)pos2.x, (int)pos2.y);
        
        float angle = (float) (Math.atan2(p2.y-p1.y,p2.x-p1.x));
        
        System.out.println(angle);
        
        return angle;
    }
}
