package dev.ufo.test;

import dev.ufo.server.object.Request;
import dev.ufo.usr.annotation.Route;
import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;

import java.util.Map;

@Route(path = "/user", type = RouteType.STANDARD)
public class UsrController implements Path {

    private final Usr usr = new Usr();

    @Override
    public String get(Request req) {

        Map<String, String> map = req.getParameters();

        if (map.isEmpty()) {
            return "not found";
        }

        if (!map.containsKey("id")){
            return "not found";
        }

        int i;

        try {
            i = Integer.parseInt(map.get("id"));
        } catch (Exception e){
            return "bad request";
        }

        return usr.getWorker(i);

    }
}
