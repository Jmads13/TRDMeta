/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
/**
 *
 * @author Pizzie
 */
public class Rotation implements Serializable {
    
    public float angle;

    public Rotation() {
    }

    public Rotation(float angle) {
        this.angle = angle;
    }
}