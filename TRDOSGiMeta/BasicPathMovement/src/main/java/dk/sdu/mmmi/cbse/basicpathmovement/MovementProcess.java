/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.basicpathmovement;

import com.decouplink.Context;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.ChaseCounter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.ASTAR;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author SoA
 */
public class MovementProcess implements IUpdateService{

    ArrayList<Point> path;
    
    @Override
    public void update(Object o, Entity entity) {
        //DONT TOUCH!
        if(path != null){
            Context ctx = context(entity);
            if (ctx.one(EntityType.class).equals(ENEMY)) {
                if(ctx.one(BehaviorType.class).equals(ASTAR)){
                    if(ctx.one(Point.class) != null){
                        //Movement
                        if(ctx.one(Position.class).x < ctx.one(Point.class).x){
                                ctx.one(Position.class).x++;
                        }else if(ctx.one(Position.class).y < ctx.one(Point.class).y){
                                ctx.one(Position.class).y++;
                        }else if(ctx.one(Position.class).x > ctx.one(Point.class).x){
                                ctx.one(Position.class).x--;
                        }else if(ctx.one(Position.class).y > ctx.one(Point.class).y){
                                ctx.one(Position.class).y--;
                        }
                        //Chase pointing
                        if(ctx.one(Position.class).x == ctx.one(Point.class).x
                                && ctx.one(Position.class).y == ctx.one(Point.class).y){
                            if(ctx.one(ChaseCounter.class) != null){
                                System.out.println(ctx.one(ChaseCounter.class).i);
                                ctx.one(ChaseCounter.class).i++;
                                if(path.size() > (ctx.one(ChaseCounter.class).i)){
                                    ctx.one(Point.class).x = path.get(ctx.one(ChaseCounter.class).i).x;
                                    ctx.one(Point.class).y = path.get(ctx.one(ChaseCounter.class).i).y;
                                    System.out.println(ctx.one(Point.class).x +" : " + ctx.one(Point.class).y);
                                }else{
                                    entity.setDestroyed(true);
                                }//End of path
                            }else{
                                ctx.add(ChaseCounter.class, new ChaseCounter(0));
                            }
                    }
                    }else{
                        ctx.add(Point.class, new Point(path.get(0)));
                    }
                }//If ASTAR
            }//If ENEMY 
        }else{
            path = Path.getInstance().getPoints(); //Hardcoded
        }
    }
}
