/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.basicpathmovement;

import dk.sdu.mmmi.cbse.common.services.IContentService;

/**
 *
 * @author SoA
 */
public class MovementContent implements IContentService{

    @Override
    public void add(Object o) {
        Path.getInstance();
    }

    @Override
    public void remove() {
    }
    
}
