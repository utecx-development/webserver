package dev.ufo.test;

import dev.ufo.usr.annotation.Route;
import dev.ufo.usr.annotation.RouteType;
import dev.ufo.usr.pathimp.Path;

@Route(path = "/", type = RouteType.HTML)
public class HtmlTest implements Path {
}
