package dev.ufo.server.executors;

import com.sun.net.httpserver.HttpExchange;
import dev.ufo.server.object.Request;
import dev.ufo.usr.pathimp.Path;

public interface Executor {

    default String execute(Request req, Path path) {
        return "404";
    };

    default void finalExecute(Request req, Path path, HttpExchange exchange) {}

}
