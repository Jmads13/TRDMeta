package pzz.updater.dynupdater;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dk.sdu.mmmi.cbse.common.services.IContentService;

/**
 *
 * @author jcs
 */
public class UpdaterImpl implements ApplicationContextAware {

    private AnnotationConfigApplicationContext appContext;
    private final ScheduledExecutorService executor;
    private List<File> jarList = new ArrayList();
    private Collection<? extends IContentService> initialList = new ArrayList();
    private Boolean jarAdded = false;
    private Object world;

    public UpdaterImpl() {
        this.executor = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = (AnnotationConfigApplicationContext) applicationContext;
        initialList = getContentServices();
    }

    public void setWorld(Object world) {
        this.world = world;
    }

    public void start() {
        this.executor.scheduleAtFixedRate(updateTask, 1000, 3000, TimeUnit.MILLISECONDS);
    }

    public void loadClass(File f) {
        URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class sysClass = URLClassLoader.class;

        try {
            Method method = sysClass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(loader, new Object[]{f.toURI().toURL()});
        } catch (NoSuchMethodException | SecurityException |
                MalformedURLException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException ex) { //holy shit
            System.out.println("Error adding class");
        }

        jarAdded = true;
    }

    private Collection<? extends IContentService> getContentServices() {
        return appContext.getBeansOfType(IContentService.class).values();
    }

    private final Runnable updateTask = new Runnable() {

        @Override
        public void run() {
            System.out.println("Scanning for new components...");
            appContext = new AnnotationConfigApplicationContext();
            //TODO: Run through all jar in plugins dir and add them dynamically to classpath
            //String addonClasspath = "/some/path/not/already/on/classpath";
            //ClassLoaderUtil.addFileToClassPath(addonClasspath);

            File folder = new File(".");
            for (File f : folder.listFiles()) {
                if (f.getPath().endsWith(".jar") && !jarList.contains(f)) {
                    jarList.add(f);
                    System.out.println("JAR added: " + f.getName());
                    loadClass(f);
                }
            }
            for (File jarFile : jarList) {
                if (!jarFile.exists()) {
                    //unload class
                    jarList.remove(jarFile);
                    System.out.println("JAR removed: " + jarFile.getName());
                    break;
                }
            }
            if (jarAdded) {
                appContext.scan("dk.sdu");
                appContext.refresh();

                for (IContentService service : getContentServices()) {
                    for (int i = 0; i < initialList.size(); i++) {
                        if (initialList.toArray()[i].getClass().getName().equals(service.getClass().getName())) {
                            break;
                        } else {
                            System.out.println("Adding service: " + service.getClass().getName());
                            service.add(world);
                        }
                    }
                }
                initialList = getContentServices();
                jarAdded = false;
            }

            System.out.println("Scan completed");

        }
    };

}
