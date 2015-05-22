package dk.sdu.mmmi.cbse.basicpathmovement;

import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    
    ServiceRegistration content;
    ServiceRegistration update;
    @Override
    public void start(BundleContext context) throws Exception {
        content = context.registerService(IContentService.class, new MovementContent(), null);
        update = context.registerService(IUpdateService.class, new MovementProcess(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        IContentService movement = (IContentService) context.getService(content.getReference());
        movement.remove();
        context.ungetService(content.getReference());
        context.ungetService(update.getReference());
    }
}
