/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author SoA
 */
public class Enemy extends Entity{
    
    
    @Override
    public void paint(float f, Entity et) {
        System.out.println("drawing: " +et.toString());
    }

    @Override
    public void update(int i, Entity et) {
        System.out.println("updating: " +et.toString());
    }
    
    
}
