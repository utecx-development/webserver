package dev.ufo.server.executors;

import dev.ufo.server.object.Request;
import dev.ufo.usr.pathimp.Path;

public class StandartExecutor implements Executor {
    @Override
    public String execute(Request req, Path controller) {
        return switch (req.getMethod()) {
            case "GET" -> controller.get(req);
            case "POST" -> controller.post(req);
            case "DELETE" -> controller.delete(req);
            case "PUT" -> controller.put(req);
            case "HEAD" -> controller.head(req);
            case "TRACE" -> controller.trace(req);
            case "CONNECT" -> controller.connect(req);
            case "PATCH" -> controller.patch(req);
            case "OPTIONS" -> controller.options(req);
            default -> "405 Method Existing";
        };
    }
}
