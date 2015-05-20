/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.astarmovement;

import dk.sdu.mmmi.cbse.common.data.MapDimension;

/**
 *
 * @author Pasoa
 */
public class Tbm {
    
    private MapDimension md = new MapDimension();
    public AStarNode startNode, endNode;
    
    public Tbm(){
        startNode = new AStarNode(4,1);
        endNode = new AStarNode(2,10);
    }
    
    private static final int[][] MAP = {
        {1,1,0,0,0,0,0,0,0,0},
        {1,1,0,0,2,2,1,1,1,1},
        {1,1,1,1,1,0,1,0,2,0},
        {1,0,0,0,1,2,1,0,2,2},
        {2,2,0,1,1,2,1,0,0,2},
        {2,2,0,1,1,1,1,0,0,2},
    };

    public int getHeightInTiles() {
        return md.getHeight();
    }

    public int getWidthInTiles() {
        return md.getWidth();
    }

    public static boolean blocked(AStarNode asn) {
        return MAP[asn.y][asn.x] != 1;
    }
}
