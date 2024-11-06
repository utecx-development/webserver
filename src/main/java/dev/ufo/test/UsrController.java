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
            return "kann nd sein";
        }

        if (!map.containsKey("id")){
            return "kann nd sein";
        }

        int i;

        try {
            i = Integer.parseInt(map.get("id"));
        } catch (Exception e){
            return "kann nd sein";
        }

        return usr.getWorker(i);

    }
}
