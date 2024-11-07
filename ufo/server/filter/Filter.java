package dev.ufo.server.filter;

import dev.ufo.server.object.Request;

public interface Filter {
    boolean doFilter(Request req);
}
