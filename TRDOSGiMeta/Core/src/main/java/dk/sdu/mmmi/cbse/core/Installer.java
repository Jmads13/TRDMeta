/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openide.modules.ModuleInstall;
import playn.core.PlayN;
import playn.java.JavaPlatform;
/**
 *
 * @author Pizzie
 */
public class Installer extends ModuleInstall {
    
    private ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
    
    @Override
    public void restored() {
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
