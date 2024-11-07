package dev.ufo.server;

import dev.ufo.server.cache.CacheItem;
import dev.ufo.server.etc.SimpleExchange;
import dev.ufo.server.object.PathMapping;
import dev.ufo.server.object.Request;
import dev.ufo.server.object.ResponseMapping;
import dev.ufo.usr.annotation.Route;
import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;

import java.util.*;

public class Router {

    private final Map<String, PathMapping> routes = new HashMap<>();

    public void registerRoute(Class<?> controllerClass, boolean cache) {

        if (controllerClass.isAnnotationPresent(Route.class) && Path.class.isAssignableFrom(controllerClass)) {
            Route route = controllerClass.getAnnotation(Route.class);
            try {
                Path path = (Path) controllerClass.getDeclaredConstructor().newInstance();
                routes.put(route.path(), new PathMapping(path, route.type(), cache));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String handleRequest(Request req, boolean cache) {

        Path controller = routes.get(req.getPath()).getPath();

        if (controller == null) {
            System.out.println(404);
            return "404 Not Found";
        }

        RouteType type = routes.get(req.getPath()).getType();

        if (type != RouteType.HTML) {

            String exc = type.getExc().execute(req, controller);
            if (cache) cache(req, exc.getBytes());
            return exc;

        } else{

            ResponseMapping exc = type.getExc().fileResponse(req, controller);
            if (cache) cache(req, exc.getBytes());

            SimpleExchange.respond(exc.getBytes(), req.getExchange(), exc.getType());
            return "--";
        }
    }

    public void cache(Request req, byte[] bytes) {
        Framework.cache.put(req.getFullPath(),
                new CacheItem(bytes, req.getFullPath()));
    }

}
