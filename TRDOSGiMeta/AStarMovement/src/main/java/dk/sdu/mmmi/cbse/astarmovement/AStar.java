/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astarmovement;

import com.decouplink.Context;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.ChaseCounter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Tile;
import dk.sdu.mmmi.cbse.common.data.types.BehaviorType;
import static dk.sdu.mmmi.cbse.common.data.types.BehaviorType.ASTAR;
import dk.sdu.mmmi.cbse.common.data.types.EntityType;
import static dk.sdu.mmmi.cbse.common.data.types.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Pasoa
 */
public class AStar implements IUpdateService{
    private static AStar instance;
    private ArrayList<AStarNode> path;
   
    private AStar(){
        path = aStaring(new AStarNode(0, 3), new AStarNode(9, 1));
    }
    
    
    
    @Override
    public void update(Object o, Entity entity) {
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
                                ctx.one(ChaseCounter.class).i++;
                                if(path.size()-1 > (ctx.one(ChaseCounter.class).i)){
                                    ctx.one(Point.class).x = (path.get(ctx.one(ChaseCounter.class).i).x*64)+32;
                                    ctx.one(Point.class).y = (path.get(ctx.one(ChaseCounter.class).i).y*64)+32;
                                }else{
                                    entity.setDestroyed(true);
                                }//End of path
                            }else{
                                ctx.add(ChaseCounter.class, new ChaseCounter(0));
                            }
                    }
                    }else{
                        int x = (path.get(0).x*64)+32;
                        int y = (path.get(0).y*64)+32;
                        ctx.add(Point.class, new Point(x, y));
                    }
                }//If ASTAR
            }//If ENEMY 
        }else{
            path = aStaring(new AStarNode(0, 3), new AStarNode(9, 1));
        }
    }
    
    //Singleton
    public static AStar getInstance(){
        if(instance != null){
            return instance;
        }
        return (instance = new AStar());
    }
    

    private ArrayList<AStarNode> aStaring(AStarNode start, AStarNode goal) {
        ArrayList<AStarNode> closed = new ArrayList();
        ArrayList<AStarNode> open = new ArrayList();
        
        start.g = 0;
        start.h = estimateDistance(start, goal);
        start.f = 0;
        
        open.add(start);
        
        while(!open.isEmpty()){
            AStarNode q = null;
            
            for(AStarNode asn : open){
                if(q == null || asn.f < q.f){
                    q = asn;
                }
            }
            
            open.remove(q);
            closed.add(q);
            for(AStarNode succesor : q.generateSuccesors()){
                if(closed.contains(succesor)){
                }else{
                    if(!open.contains(succesor)){
                        succesor.g = q.g+1;
                        succesor.h = estimateDistance(succesor, goal);
                        succesor.f = succesor.g + succesor.h;
                        succesor.parent = q;
                        open.add(succesor);
                    }
                    if(succesor.x == goal.x && succesor.y == goal.y){ //path found
                        return reconstructPath(start, succesor);
                    }
                }
            }
        }
        return null;
    }
    
    public int estimateDistance(AStarNode node1, AStarNode node2) {
        return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
    }
    
    private ArrayList<AStarNode> reconstructPath(AStarNode startNode, AStarNode node) {
        ArrayList<AStarNode> path = new ArrayList<>();
        while(!(node.parent == null)) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        path.add(startNode);
        return path;
    }
    
    
}
