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
        if(x+1 <= md.getHeight()){
            AStarNode temp = new AStarNode(x+1,y);
            temp.parent = this;
            neighbors.add(temp);
        }
        if(x-1 > 0){
            AStarNode temp = new AStarNode(x-1,y);
            temp.parent = this;
            neighbors.add(temp);
        }
        if(y+1 <= md.getWidth()){
            AStarNode temp = new AStarNode(x,y+1);
            temp.parent = this;
            neighbors.add(temp);
        }
        if(y-1 > 0){
            AStarNode temp = new AStarNode(x,y-1);
            temp.parent = this;
            neighbors.add(temp);
        }
        return neighbors;
    }
}
