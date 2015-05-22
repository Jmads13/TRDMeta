package dk.sdu.mmmi.cbse.dynupdater;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class Activator implements BundleActivator {

    private BundleContext context;
    private HashMap<File, Long> jarList = new HashMap();

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(dynInstaller, 3000, 3000, TimeUnit.MILLISECONDS);
        System.out.println("DynUpdater started, checking for plugins");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("DynUpdater has been stopped");
    }

    private final Runnable dynInstaller = new Runnable() {

        @Override
        public void run() {
            //Checking if plugin folder exists, creating it if it does not
            File folder = new File(".//plugins");
            if(!folder.exists()) {
                folder.mkdir();
            }
            //Checking for new plugins
            for (File file : folder.listFiles()) {
                if (file.getName().endsWith(".jar") && !jarList.containsKey(file)) {
                    try {
                        System.out.println("Found new JAR: "+file.getName());
                        context.installBundle(file.toURI().toString());
                        context.getBundles()[context.getBundles().length-1].start();
                        jarList.put(file, context.getBundles()[context.getBundles().length-1].getBundleId());
                    } catch (BundleException ex) {
                        System.out.println(ex);
                    }
                }
            }
            //Checking for removed plugins
            for(File jar : jarList.keySet()) {
                if(!jar.exists()) {
                    try {
                        System.out.println("Removed JAR: "+jar.getName());
                        context.getBundle(jarList.get(jar)).stop();
                        jarList.remove(jar);
                    } catch (BundleException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    };

}
