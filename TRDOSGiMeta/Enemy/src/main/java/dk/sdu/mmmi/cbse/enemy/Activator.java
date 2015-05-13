package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.services.IContentService;
import dk.sdu.mmmi.cbse.common.services.IUpdateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    ServiceRegistration content;
    ServiceRegistration update;
    public void start(BundleContext context) throws Exception {
        content = context.registerService(IContentService.class, new Enemy(), null);
        update = context.registerService(IUpdateService.class, new EnemyProcess(), null);
    }

    public void stop(BundleContext context) throws Exception {
        IContentService enemy = (IContentService) context.getService(content.getReference());
        enemy.remove();
        context.ungetService(content.getReference());
        context.ungetService(update.getReference());
    }

}
