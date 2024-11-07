package dev.ufo.server.executors;

import dev.ufo.server.object.Request;
import dev.ufo.server.object.ResponseMapping;
import dev.ufo.usr.pathimp.Path;

public interface Executor {

    default String execute(Request req, Path path) {
        return "404";
    };

    default ResponseMapping fileResponse(Request req, Path path) {
        return null;
    }

}
