/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.javalauncher;

import dk.sdu.mmmi.cbse.core.TRDMain;
import playn.core.PlayN;
import playn.java.JavaPlatform;
/**
 *
 * @author Pizzie
 */
public class TRDGameJava {
    
    public static void main(String[] args) {
        JavaPlatform.Config config = new JavaPlatform.Config();
        JavaPlatform.register(config);
        PlayN.run(new TRDMain(33));
    }
}
