/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.pewpew;

import com.decouplink.DisposableList;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameTime;
import dk.sdu.mmmi.cbse.common.data.ImageAsset;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.BULLET;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

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
        if(context(e).one(EntityType.class).equals(EntityType.BULLET)){
            Position pos = context(e).one(Position.class);
            if(checkBounds(pos)){
            Velocity velocity = context(e).one(Velocity.class);

            pos.x += velocity.vectorX;
            pos.y += velocity.vectorY;
            }else{
                e.setDestroyed(true);
            }
        }
        
        List<Entity> entities = new ArrayList<Entity>(context(o).all(Entity.class));
        //Collections.reverse(entities);
        disposables.dispose();  
        
        for(Entity target : entities){
            if(context(target).one(EntityType.class).equals(EntityType.ENEMY)){
                if(context(e).one(EntityType.class).equals(EntityType.TOWER)){
                    if(testRange(e, target)){
                        Rotation rotation = context(e).one(Rotation.class);
                        rotation.angle = calcAngle(e, target);
                        if(context(e).one(GameTime.class).delta < 0){
                            shoot(o, e);
                            context(e).one(GameTime.class).delta = 10;
                        }
                        context(e).one(GameTime.class).delta--;
                        break;
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
        
        return angle;
    }

    private void shoot(Object o, Entity e) {
        Position p = context(e).one(Position.class);
        Rotation r = context(e).one(Rotation.class);
        
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/Bullet1.png").toExternalForm();
        
        Entity bullet = new Entity();
        context(bullet).add(EntityType.class, BULLET);
        context(bullet).add(ImageAsset.class, new ImageAsset("images/Bullet1.png"));
        context(bullet).add(Position.class, new Position(p.x, p.y));
        context(bullet).add(Rotation.class, new Rotation(r.angle));
        context(bullet).add(Velocity.class, new Velocity((float) (Math.cos(r.angle) * 10), (float) (Math.sin(r.angle) * 10)));
        context(bullet).add(Scale.class, new Scale(1f,1f));
        context(o).add(Entity.class, bullet);
    }

    private boolean checkBounds(Position p) {
        if(p.x < 0 || p.x > 640 || p.y < 0 || p.y > 480-96){
            return false;
        }
        return true;
    }
}
