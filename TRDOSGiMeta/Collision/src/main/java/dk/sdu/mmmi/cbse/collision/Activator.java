package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    ServiceRegistration update;
    @Override
    public void start(BundleContext context) throws Exception {
        update = context.registerService(IUpdateService.class, new CollisionDetection(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(update.getReference());
    }

}
