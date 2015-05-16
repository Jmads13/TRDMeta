/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astarmovement;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import java.util.ArrayList;


/**
 *
 * @author Pasoa
 */
public class AStar implements IUpdateService{
    private static AStar instance;
    private boolean runThrough;
    
    private AStar(){
        runThrough = true;
    }
    
    
    
    @Override
    public void update(Object o, Entity entity) {
        if(runThrough){
            System.out.println("**************runThrough**************");
            aStaring(o, new AStarNode(4, 1), new AStarNode(2, 10));
            System.out.println("**************ranThrough**************");
            runThrough = false;
        }
    }
    
    //Singleton
    public static AStar getInstance(){
        if(instance != null){
            return instance;
        }
        return (instance = new AStar());
    }
    

    private ArrayList<AStarNode> aStaring(Object o, AStarNode start, AStarNode goal) {
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
                System.out.println("evaluating succesor: " + succesor.x + ", "+succesor.y);
                if(closed.contains(succesor)){
                    System.out.println("Closed contained succesor");
                    //TODO Check if walkable 
                }else{
                    if(!open.contains(succesor)){
                        succesor.g = q.g+1;
                        succesor.h = estimateDistance(succesor, goal);
                        succesor.f = succesor.g + succesor.h;
                        succesor.parent = q;
                        open.add(succesor);
                    }else{
                        float nextG = q.g + succesor.cost;
                        if(nextG < succesor.g){
                            open.remove(succesor);
                            closed.add(succesor);
                        }
                    }
                    if(succesor.x == goal.x && succesor.y == goal.y){ //path found
                        System.out.println("hurray A* have generated a path from: ("+ start.x+","+start.y + ") to ("+goal.x+","+goal.y+")");
                        System.out.println("You derserve a beer!");
                        return reconstructPath(succesor);
                    }
                }
            }
        }
        return null;
    }
    
    public int estimateDistance(AStarNode node1, AStarNode node2) {
        return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
    }
    
    private ArrayList<AStarNode> reconstructPath(AStarNode node) {
        ArrayList<AStarNode> path = new ArrayList<>();
        while(!(node.parent == null)) {
            path.add(node);
            node = node.parent;
        }
        return path;
    }
    
    
}
