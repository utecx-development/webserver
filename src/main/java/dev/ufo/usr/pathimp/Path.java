package dev.ufo.usr.pathimp;

import dev.ufo.server.object.Request;

public interface Path {

    default String get(Request req) {
        //TODO: add support for default html pages (404 in this case)
        return "404";
    }

    default String delete(Request req) { return "404 Not Found"; }

    default String post(Request req) { return "404 Not Found"; }

    default String head(Request req) { return "404 Not Found"; }

    default String put(Request req) { return "404 Not Found"; }

    default String trace(Request req) { return "404 Not Found"; }

    default String connect(Request req) { return "404 Not Found"; }

    default String patch(Request req) { return "404 Not Found"; }

    default String options(Request req) { return "404 Not Found"; }

}
