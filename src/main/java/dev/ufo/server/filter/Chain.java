package dev.ufo.server.filter;

import dev.ufo.server.object.Request;

import java.util.ArrayList;
import java.util.List;

public class Chain {

    private static final List<Filter> filters = new ArrayList<>();

    public static void addFilter(Filter filter) {
        filters.add(filter);
    }

    public static boolean filterRequest(Request request) {
        for (Filter filter : filters){
            if (!filter.doFilter(request)) {
                return false;
            }
        }
        return true;
    }

}
