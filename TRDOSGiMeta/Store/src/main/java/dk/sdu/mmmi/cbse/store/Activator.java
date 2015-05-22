package dk.sdu.mmmi.cbse.store;

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
        content = context.registerService(IContentService.class, new Store(), null);
        update = context.registerService(IUpdateService.class, new StoreProcess(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        IContentService store = (IContentService) context.getService(content.getReference());
        store.remove();
        context.ungetService(content.getReference());
        context.ungetService(update.getReference());
    }

}
