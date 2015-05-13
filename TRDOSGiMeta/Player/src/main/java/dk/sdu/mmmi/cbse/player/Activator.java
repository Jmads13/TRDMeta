package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    ServiceRegistration content;
    ServiceRegistration update;
    public void start(BundleContext context) throws Exception {
        content = context.registerService(IContentService.class, new Player(), null);
        update = context.registerService(IUpdateService.class, new PlayerProcess(), null);
    }

    public void stop(BundleContext context) throws Exception {
        IContentService player = (IContentService) context.getService(content.getReference());
        player.remove();
        context.ungetService(content.getReference());
        context.ungetService(update.getReference());
    }

}
