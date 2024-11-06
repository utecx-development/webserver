package dev.ufo.server;

import com.sun.net.httpserver.HttpExchange;
import dev.ufo.server.object.PathMapping;
import dev.ufo.server.object.Request;
import dev.ufo.usr.annotation.Route;
import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, PathMapping> routes = new HashMap<>();

    public void registerRoute(Class<?> controllerClass) {
        if (controllerClass.isAnnotationPresent(Route.class) && Path.class.isAssignableFrom(controllerClass)) {
            Route route = controllerClass.getAnnotation(Route.class);
            try {
                Path path = (Path) controllerClass.getDeclaredConstructor().newInstance();
                routes.put(route.path(), new PathMapping(path, route.type()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String handleRequest(Request req, HttpExchange exchange) {

        Path controller = routes.get(req.getPath()).getPath();

        if (controller == null) {
            System.out.println(404);
            return "404 Not Found";
        }

        RouteType type = routes.get(req.getPath()).getType();

        if (type != RouteType.HTML) {
            return type.getExc().execute(req, controller);
        } else{
            type.getExc().finalExecute(req, controller, exchange);
            return "--";
        }
    }

}
