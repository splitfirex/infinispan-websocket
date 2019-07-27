package app.components.cache;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Component("localCacheManager")
public class LocalCacheManager {

    private EmbeddedCacheManager cacheManager;
    private Cache<String, Object> cache;
    private Boolean cacheCreated = false;

    @Autowired
    private ClusterListener clusterListener;

    @Autowired
    private CacheListener cacheListener;

    public void initCache(String clustename) {
        //cacheManager = new DefaultCacheManager(LocalCacheManager.class.getResourceAsStream("/weatherapp-infinispan.xml"));
        cacheCreated = true;
        cacheManager = new DefaultCacheManager(
                new GlobalConfigurationBuilder().globalJmxStatistics().allowDuplicateDomains(true)
                        .transport().clusterName(clustename).defaultTransport().build(),
                new ConfigurationBuilder().clustering()

                        .cacheMode(CacheMode.REPL_SYNC)
                        .build());
        cacheManager.addListener(clusterListener);
        cache = cacheManager.getCache(clustename);
        cache.addListener(cacheListener);
        if (cacheManager.isCoordinator())
            System.out.println("---- Coordinator Start ----");

    }

    public EmbeddedCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(EmbeddedCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Cache<String, Object> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Object> cache) {
        this.cache = cache;
    }

    public Boolean getCacheCreated() {
        return cacheCreated;
    }

    public void setCacheCreated(Boolean cacheCreated) {
        this.cacheCreated = cacheCreated;
    }
}
