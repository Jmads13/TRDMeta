package dk.sdu.mmmi.cbse.ballistics;

import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    ServiceRegistration update;
    @Override
    public void start(BundleContext context) throws Exception {
        update = context.registerService(IUpdateService.class, new Shooting(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(update.getReference());
    }

}
