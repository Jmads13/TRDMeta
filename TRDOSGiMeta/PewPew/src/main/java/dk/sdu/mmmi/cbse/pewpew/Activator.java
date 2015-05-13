package dk.sdu.mmmi.cbse.pewpew;

import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IUpdateService.class, new Shooting(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
