package dev.ufo.usr.annotation;

import dev.ufo.server.executors.Executor;
import dev.ufo.server.executors.FileExecutor;
import dev.ufo.server.executors.JsonBasedRestExecutor;
import dev.ufo.server.executors.StandartExecutor;
import lombok.Getter;

@Getter
public enum RouteType {
    HTML(new FileExecutor()),
    STANDARD(new StandartExecutor()),
    REST(new JsonBasedRestExecutor());

    final Executor exc;

    RouteType(Executor exc){
        this.exc = exc;
    }
}
