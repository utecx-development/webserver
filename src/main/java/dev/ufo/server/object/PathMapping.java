package dev.ufo.server.object;

import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PathMapping {

    private Path path;
    private RouteType type;

}
