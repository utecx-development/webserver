package dev.ufo.server;

import dev.ufo.server.cache.Cache;
import dev.ufo.server.cache.CacheItem;
import dev.ufo.server.cache.hashmapcache.HashMapCache;
import dev.ufo.server.etc.SimpleExchange;
import dev.ufo.server.filter.Chain;
import dev.ufo.server.filter.Filter;
import dev.ufo.server.object.Request;

import java.util.HashMap;

public class Framework {
    private final Router router = new Router();
    //public static final Cache<CacheItem> cache = new HashMapCache<>();

    public static final HashMap<String, CacheItem> cache = new HashMap<>();

    public void registerController(Class<?> controllerClass, boolean cache) {
        router.registerRoute(controllerClass, cache);
    }

    public void registerController(boolean cache, Class<?>... controllerClasses) {
        for (Class<?> clazz : controllerClasses) {
            registerController(clazz, cache);
        }
    }

    public void addFilter(Filter filter) {
        Chain.addFilter(filter);
    }

    public String handleRequest(Request request) {
        if (Chain.filterRequest(request)) {

            if (cache.containsKey(request.getFullPath())) {

                if (cache.get(request.getPath()) != null) {

                    CacheItem item = cache.get(request.getPath());
                    SimpleExchange.respond(item.getBytes(), request.getExchange(), item.getHtmlType());
                    return "--";

                } else {
                    return router.handleRequest(request, true);
                }

            }

            return router.handleRequest(request, true);
        }
        return "403 Forbidden";
    }
}
