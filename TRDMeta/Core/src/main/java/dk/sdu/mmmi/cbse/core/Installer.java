/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.core;

import org.openide.modules.ModuleInstall;
import playn.core.PlayN;
import playn.java.JavaPlatform;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        JavaPlatform.Config config = new JavaPlatform.Config();
        JavaPlatform.register(config);
        PlayN.run(new TRDMain(33));
    }

}
