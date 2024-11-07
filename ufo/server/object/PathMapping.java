package dev.ufo.server.object;

import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathMapping {

    private Path path;
    private RouteType type;

    public PathMapping(Path path, RouteType type, boolean cache) {
        this.path = path;
        this.type = type;
    }

}
