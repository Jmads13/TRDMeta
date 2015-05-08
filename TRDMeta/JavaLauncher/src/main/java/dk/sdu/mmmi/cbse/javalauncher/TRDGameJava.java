/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.javalauncher;

import dk.sdu.mmmi.cbse.core.TRDMain;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import playn.core.PlayN;
import playn.java.JavaPlatform;
/**
 *
 * @author Pizzie
 */
public class TRDGameJava {
    
    private static ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
    
    public static void main(String[] args) {
        JavaPlatform.Config config = new JavaPlatform.Config();
        JavaPlatform.register(config);
        
        exe.schedule(new Runnable() {
            @Override
            public void run() {
                PlayN.run(new TRDMain(33));
            }
        }, 3, TimeUnit.SECONDS);
        
    }
}
