package dev.ufo.server;

import com.sun.net.httpserver.HttpExchange;
import dev.ufo.server.filter.Chain;
import dev.ufo.server.filter.Filter;
import dev.ufo.server.object.Request;

public class Framework {
    private final Router router = new Router();

    public void registerController(Class<?> controllerClass) {
        router.registerRoute(controllerClass);
    }

    public void addFilter(Filter filter) {
        Chain.addFilter(filter);
    }

    public String handleRequest(Request request, HttpExchange exchange) {
        if (Chain.filterRequest(request)) {
            return router.handleRequest(request, exchange);
        }
        return "403 Forbidden";
    }
}
