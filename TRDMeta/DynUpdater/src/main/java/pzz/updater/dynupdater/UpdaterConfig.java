package pzz.updater.dynupdater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jcs
 */
@Configuration
public class UpdaterConfig {

    @Bean(name="DynUpdater")
    public UpdaterImpl createUpdater() {
        UpdaterImpl u = new UpdaterImpl();
        u.start();
        return u;
    }
}
