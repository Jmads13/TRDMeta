/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astarmovement;

import dk.sdu.mmmi.cbse.common.data.MapDimension;
import java.util.ArrayList;

/**
 *
 * @author Pasoa
 */
public class AStarNode {

    private MapDimension md = new MapDimension();
    public AStarNode parent;
    public int x,y;
    public int f,g,h;
    public int cost = 1;
    
    public AStarNode(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int hashCode(){
        return x*y;
    }

    @Override
    public boolean equals(Object o){
      if (o instanceof AStarNode){
        return x==((AStarNode)o).x && y==((AStarNode)o).y;
      }
      return false;
    }
    
    //Looking up 4 neighbors and adding to node;
    public ArrayList<AStarNode> generateSuccesors(){
        ArrayList<AStarNode> neighbors = new ArrayList<>();
        if(x+1 <= 9){
            AStarNode temp = new AStarNode(x+1,y);
            if(!Tbm.blocked(temp)){
                System.out.println("x+1 is true");
                temp.parent = this;
                neighbors.add(temp);
            }
        }
        if(x-1 >= 0){
            AStarNode temp = new AStarNode(x-1,y);
            if(!Tbm.blocked(temp)){
                System.out.println("x-1 is true");
                temp.parent = this;
                neighbors.add(temp);
            }
        }
        if(y+1 <= 5){
            
            AStarNode temp = new AStarNode(x,y+1);
            System.out.println(temp.x + ", "+ temp.y);
            if(!Tbm.blocked(temp)){
                System.out.println("y+1 is true");
                temp.parent = this;
                neighbors.add(temp);
            }
        }
        if(y-1 >= 0){
            AStarNode temp = new AStarNode(x,y-1);
            if(!Tbm.blocked(temp)){
                System.out.println("y-1 is true");
                temp.parent = this;
                neighbors.add(temp);
            }
        }
        return neighbors;
    }
}