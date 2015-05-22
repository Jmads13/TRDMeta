package dk.sdu.mmmi.cbse.tilemap;

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
        content = context.registerService(IContentService.class, new TileMap(), null);
        update = context.registerService(IUpdateService.class, new TileMapProcess(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        IContentService tilemap = (IContentService) context.getService(content.getReference());
        tilemap.remove();
        context.ungetService(content.getReference());
        context.ungetService(update.getReference());
    }

}
