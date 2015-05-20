/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data;

/**
 *
 * @author Pasoa
 */
public class MapDimension {
    
    private int length = 9, height = 5, tileSize = 64;
    
    public int getWidth(){
        return length;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getTileSize(){
        return tileSize;
    }
}
