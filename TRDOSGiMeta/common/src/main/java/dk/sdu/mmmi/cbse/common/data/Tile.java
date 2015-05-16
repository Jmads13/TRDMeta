/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SoA
 */
public class Tile {
    
    public int x,y,f,g,h,cost;
    private boolean blocked;
    private boolean visited;
    public Tile parent;

    public List<Tile> neighbors = new ArrayList<Tile>();
    
    public Tile getParent() {
        return parent;
    }

    public Tile(int x,int y, boolean blocked){
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    public boolean getBlocked(){
        return blocked;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
}
